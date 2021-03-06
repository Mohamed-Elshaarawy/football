package net.elshaarawy.football.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import net.elshaarawy.football.FootballApp
import net.elshaarawy.football.data.room.daos.FixturesDao
import net.elshaarawy.football.data.room.daos.LeaguesDao
import net.elshaarawy.football.data.room.daos.TeamsDao
import net.elshaarawy.football.data.room.entities.FixtureEntity
import net.elshaarawy.football.data.room.entities.LeagueEntity
import net.elshaarawy.football.data.room.entities.TeamEntity

/**
 * Created by elshaarawy on 7/25/18.
 */
const val DATABASE_NAME = "FootballDatabase"

@Database(version = 1, exportSchema = false,
        entities = [(LeagueEntity::class), (TeamEntity::class), (FixtureEntity::class)])
abstract class FootballDatabase : RoomDatabase() {

    abstract fun leaguesDao(): LeaguesDao

    abstract fun teamsDao(): TeamsDao

    abstract fun fixturesDao(): FixturesDao

    companion object {
        private var footballDatabase: FootballDatabase? = null

        /*we must call fallbackToDestructiveMigration from the builder
        to make migration from old schema to the new one smoothly*/
        @Synchronized
        fun getInstance(): FootballDatabase = footballDatabase
                ?: Room.databaseBuilder(FootballApp.context(), FootballDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build().also { footballDatabase = it }

    }
}

