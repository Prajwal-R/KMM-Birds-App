//package com.prajwal.shared.cache
//
//import dev.prajwal.shared.model.BirdImage
//
//internal class DatabaseClass(databaseDriverFactory: DatabaseDriverFactory) {
//    private val database = BirdsDataBase(databaseDriverFactory.createDriver())
//    private val dbQuery = database.appDatabaseQueries
//
//    internal fun addData(birdsList: List<BirdImage>) {
//        dbQuery.transaction {
//            birdsList.forEach { birdImage ->
//                dbQuery.insertBirds(birdImage)
//            }
//        }
//    }
//
//    internal fun clearDatabase() {
//        dbQuery.transaction {
//            dbQuery.removeAllBirds()
//        }
//    }
//
//    internal fun getAllBirds(): List<BirdImage> {
//        return dbQuery.transaction {
//            dbQuery.selectAllBirds(::BirdImage).executeAsList()
//        }
//    }
//}