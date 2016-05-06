package com.intel.kotlinmuseummap.ios.db

import com.intel.moe.natj.general.ptr.Ptr
import com.intel.moe.natj.general.ptr.VoidPtr
import com.intel.moe.natj.general.ptr.impl.PtrFactory
import com.intel.kotlinmuseummap.common.core.Utils
import com.intel.kotlinmuseummap.common.database.ISQLiteDatabase
import com.intel.kotlinmuseummap.common.database.ISQLiteDatabaseHelper
import com.intel.kotlinmuseummap.common.model.entities.MuseumEntity
import ios.foundation.c.Foundation
import ios.foundation.enums.NSSearchPathDirectory
import ios.foundation.enums.NSSearchPathDomainMask
import org.sqlite.c.Globals
import java.io.File
import java.io.IOException
import java.io.InputStream

class SQLiteDatabaseHelper(private val databaseFile: String) : ISQLiteDatabaseHelper {

    private var connectionHandle: VoidPtr? = null

    init {
        try {
            init()
        } catch (e: Exception) {
            connectionHandle = null
        }

    }

    @Throws(IOException::class)
    private fun init() {
        // Get path to database
        val docPath = documentsPath
        if (docPath == null) {
            System.err.println("Failed to load app's document path")
            return
        }
        val file = File(docPath, databaseFile)

        // Check existence
        val isNew = !file.exists()

        // Open database
        @SuppressWarnings("unchecked")
        val dbHandleRef = PtrFactory.newPointerPtr(Void::class.java, 2, 1, true, false) as Ptr<VoidPtr>
        if (Globals.sqlite3_open(file.canonicalPath, dbHandleRef) != 0) {
            throw IOException("Failed to open/create database file")
        }
        connectionHandle = dbHandleRef.get()

        // Initialize
        if (isNew) {
            onCreate(writableDatabase)
        } else {
            // onUpdate(getWritableDatabase());
        }
    }

    private fun onCreate(sqLiteDatabase: ISQLiteDatabase) {
        Utils.executeSQLStatement(sqLiteDatabase, Utils.createTableSQL(MuseumEntity.TABLE_NAME,
                MuseumEntity.fields))
    }

    @SuppressWarnings("unused")
    private fun onUpdate(sqLiteDatabase: ISQLiteDatabase) {
        Utils.executeSQLStatement(sqLiteDatabase, Utils.dropTableIfExistsSQL(MuseumEntity.TABLE_NAME))
        onCreate(sqLiteDatabase)
    }

    private val documentsPath: String?
        get() {
            val paths = Foundation.NSSearchPathForDirectoriesInDomains(
                    NSSearchPathDirectory.DocumentDirectory,
                    NSSearchPathDomainMask.UserDomainMask, true)
            return paths.firstObject()
        }

    override val writableDatabase: ISQLiteDatabase
        get() = SQLiteDatabase(connectionHandle)

    override fun close() {
        if (connectionHandle != null) {
            Globals.sqlite3_close(connectionHandle)
            connectionHandle = null
        }
    }

    override val defaultDatabaseContents: InputStream?
        get() = null

}