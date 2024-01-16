package dev.prajwal.shared.cache

import app.cash.sqldelight.db.SqlDriver
import dev.prajwal.shared.model.BirdImage

//expect class DatabaseDriverFactory {
//    fun createDriver(): SqlDriver
//}
//
//
//fun createDatabase(driverFactory: DatabaseDriverFactory): BirdsDatabase {
//    val driver = driverFactory.createDriver()
//    return BirdsDatabase(driver)
//}
//
//fun addData(birdsList: List<BirdImage>) {
////    val database = createDatabase()
//    val dbQuery = database.appDatabaseQueries
//        dbQuery.transaction {
//            birdsList.forEach { birdImage ->
//                dbQuery.insertBirds(birdImage)
//            }
//        }
//    }