// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package ke.co.appslab.mu_graduation.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbHelper extends SQLiteOpenHelper
{

    public static final String AWARDS_IMAGE = "award_image";
    public static final String AWARDS_NAME = "award_name";
    public static final String AWARDS_ROWID = "_id";
    public static final String COURSES_NAME = "course_name";
    public static final String COURSES_ROWID = "_id";
    public static final String COURSES_SCHOOL_ID = "school_id";
    private static final String CREATE_AWARDS_TABLE = "CREATE TABLE awards(_id INTEGER PRIMARY KEY AUTOINCREMENT, award_name TEXT NOT NULL, award_image TEXT NOT NULL);";
    private static final String CREATE_COURSES_TABLE = "CREATE TABLE courses(_id INTEGER PRIMARY KEY AUTOINCREMENT, course_name TEXT NOT NULL, school_id TEXT NOT NULL);";
    private static final String CREATE_GRADUANDS_TABLE = "CREATE TABLE graduands(_id INTEGER PRIMARY KEY AUTOINCREMENT, graduands_name TEXT NOT NULL, course_id TEXT NOT NULL, honours_id TEXT NOT NULL, majors_id TEXT NOT NULL, graduand_image TEXT NOT NULL);";
    private static final String CREATE_GRAD_AWARDS_TABLE = "CREATE TABLE graduand_awards(_id INTEGER PRIMARY KEY AUTOINCREMENT, graduand_id TEXT NOT NULL, award_id TEXT NOT NULL);";
    private static final String CREATE_HONOURS_TABLE = "CREATE TABLE honours(_id INTEGER PRIMARY KEY AUTOINCREMENT, honours_name TEXT NOT NULL, honours_priority TEXT NOT NULL);";
    private static final String CREATE_MAJORS_TABLE = "CREATE TABLE majors(_id INTEGER PRIMARY KEY AUTOINCREMENT, majors_name TEXT NOT NULL, course_id TEXT NOT NULL);";
    private static final String CREATE_SCHEDULE_TABLE = "CREATE TABLE schedule(_id INTEGER PRIMARY KEY AUTOINCREMENT, task TEXT NOT NULL, desc TEXT NOT NULL, time TEXT NOT NULL, image TEXT NOT NULL);";
    private static final String CREATE_SCHOOLS_TABLE = "CREATE TABLE schools(_id INTEGER PRIMARY KEY AUTOINCREMENT, school_name TEXT NOT NULL, school_image TEXT NOT NULL);";
    private static final String CREATE_SPEAKERS_TABLE = "CREATE TABLE speakers(_id INTEGER PRIMARY KEY AUTOINCREMENT, speaker_name TEXT NOT NULL, speaker_title TEXT NOT NULL, speaker_bio TEXT NOT NULL, speaker_image TEXT NOT NULL);";
    private static final String DATABASE_NAME = "mu_graduation.db";
    private static final int DATABASE_VERSION = 1;
    public static final String GRADUANDS_COURSE_ID = "course_id";
    public static final String GRADUANDS_HONOURS_ID = "honours_id";
    public static final String GRADUANDS_IMAGE = "graduand_image";
    public static final String GRADUANDS_MAJORS_ID = "majors_id";
    public static final String GRADUANDS_NAME = "graduands_name";
    public static final String GRADUANDS_ROWID = "_id";
    public static final String GRAD_AWARDS_AWARD = "award_id";
    public static final String GRAD_AWARDS_GRADUAND = "graduand_id";
    public static final String GRAD_AWARDS_ROWID = "_id";
    public static final String HONOURS_NAME = "honours_name";
    public static final String HONOURS_PRIORITY = "honours_priority";
    public static final String HONOURS_ROWID = "_id";
    public static final String MAJORS_COURSE_ID = "course_id";
    public static final String MAJORS_NAME = "majors_name";
    public static final String MAJORS_ROWID = "_id";
    public static final String SCHEDULE_DESCRIPTION = "desc";
    public static final String SCHEDULE_IMAGE = "image";
    public static final String SCHEDULE_ROWID = "_id";
    public static final String SCHEDULE_TASK = "task";
    public static final String SCHEDULE_TIME = "time";
    public static final String SCHOOLS_IMAGE = "school_image";
    public static final String SCHOOLS_NAME = "school_name";
    public static final String SCHOOLS_ROWID = "_id";
    public static final String SPEAKERS_BIO = "speaker_bio";
    public static final String SPEAKERS_IMAGE = "speaker_image";
    public static final String SPEAKERS_NAME = "speaker_name";
    public static final String SPEAKERS_ROWID = "_id";
    public static final String SPEAKERS_TITLE = "speaker_title";
    public static final String TABLE_AWARDS = "awards";
    public static final String TABLE_COURSES = "courses";
    public static final String TABLE_GRADUANDS = "graduands";
    public static final String TABLE_GRAD_AWARDS = "graduand_awards";
    public static final String TABLE_HONOURS = "honours";
    public static final String TABLE_MAJORS = "majors";
    public static final String TABLE_SCHEDULE = "schedule";
    public static final String TABLE_SCHOOLS = "schools";
    public static final String TABLE_SPEAKERS = "speakers";
    private  final String TAG = dbHelper.this.getClass().getSimpleName();

    public dbHelper(Context context)
    {
        super(context, "mu_graduation.db", null, 2);
    }

    public void addAward(String s, String s1, String s2)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_id", s);
        contentvalues.put("award_name", s1);
        contentvalues.put("award_image", s2);
        long l = sqlitedatabase.insert("awards", null, contentvalues);
        sqlitedatabase.close();
        Log.d(TAG, (new StringBuilder()).append("New awards inserted into sqlite: ").append(l).toString());
    }

    public void addCourse(String s, String s1, String s2)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_id", s);
        contentvalues.put("course_name", s1);
        contentvalues.put("school_id", s2);
        long l = sqlitedatabase.insert("courses", null, contentvalues);
        sqlitedatabase.close();
        Log.d(TAG, (new StringBuilder()).append("New course inserted into sqlite: ").append(l).toString());
    }

    public void addGraduand(String s, String s1, String s2, String s3, String s4, String s5)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_id", s);
        contentvalues.put("graduands_name", s1);
        contentvalues.put("course_id", s2);
        contentvalues.put("honours_id", s3);
        contentvalues.put("majors_id", s4);
        contentvalues.put("graduand_image", s5);
        long l = sqlitedatabase.insert("graduands", null, contentvalues);
        sqlitedatabase.close();
        Log.d(TAG, (new StringBuilder()).append("New graduand awards inserted into db: ").append(l).toString());
    }

    public void addGraduandAward(String s, String s1, String s2)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_id", s);
        contentvalues.put("graduand_id", s1);
        contentvalues.put("award_id", s2);
        long l = sqlitedatabase.insert("graduand_awards", null, contentvalues);
        sqlitedatabase.close();
        Log.d(TAG, (new StringBuilder()).append("New graduand awards inserted into db: ").append(l).toString());
    }

    public void addHonours(String s, String s1, String s2)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_id", s);
        contentvalues.put("honours_name", s1);
        contentvalues.put("honours_priority", s2);
        long l = sqlitedatabase.insert("honours", null, contentvalues);
        sqlitedatabase.close();
        Log.d(TAG, (new StringBuilder()).append("New honours inserted into sqlite: ").append(l).toString());
    }

    public void addMajor(String s, String s1, String s2)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_id", s);
        contentvalues.put("majors_name", s1);
        contentvalues.put("course_id", s2);
        long l = sqlitedatabase.insert("majors", null, contentvalues);
        sqlitedatabase.close();
        Log.d(TAG, (new StringBuilder()).append("New major inserted into sqlite: ").append(l).toString());
    }

    public void addSchedule(String s, String s1, String s2, String s3, String s4)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_id", s);
        contentvalues.put("task", s1);
        contentvalues.put("desc", s2);
        contentvalues.put("time", s3);
        contentvalues.put("image", s4);
        long l = sqlitedatabase.insert("schedule", null, contentvalues);
        sqlitedatabase.close();
        Log.d(TAG, (new StringBuilder()).append("New schedule item inserted into sqlite: ").append(l).toString());
    }

    public void addSchool(String s, String s1, String s2)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_id", s);
        contentvalues.put("school_name", s1);
        contentvalues.put("school_image", s2);
        long l = sqlitedatabase.insert("schools", null, contentvalues);
        sqlitedatabase.close();
        Log.d(TAG, (new StringBuilder()).append("New school/faculty inserted into sqlite: ").append(l).toString());
    }

    public void addSpeaker(String s, String s1, String s2, String s3, String s4)
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("_id", s);
        contentvalues.put("speaker_name", s1);
        contentvalues.put("speaker_title", s2);
        contentvalues.put("speaker_bio", s3);
        contentvalues.put("speaker_image", s4);
        long l = sqlitedatabase.insert("speakers", null, contentvalues);
        sqlitedatabase.close();
        Log.d(TAG, (new StringBuilder()).append("New speaker inserted into sqlite: ").append(l).toString());
    }

    public void deleteInfo()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("schedule", null, null);
        sqlitedatabase.delete("speakers", null, null);
        sqlitedatabase.delete("schools", null, null);
        sqlitedatabase.delete("courses", null, null);
        sqlitedatabase.delete("graduands", null, null);
        sqlitedatabase.delete("honours", null, null);
        sqlitedatabase.delete("majors", null, null);
        sqlitedatabase.close();
    }

    public Cursor filterGraduandsList(String s)
    {
        s = (new StringBuilder()).append("%").append(s).append("%").toString();
        s = (new StringBuilder()).append("SELECT * FROM graduands, courses WHERE graduands_name LIKE '").append(s).append("' ").append(" AND ").append("graduands").append(".").append("course_id").append(" = ").append("courses").append(".").append("_id").append(" ORDER BY ").append("graduands_name").append(" ASC").toString();
        return getWritableDatabase().rawQuery(s, null);
    }

    public Cursor getAllAwardedGraduands(long l)
    {
        String s = (new StringBuilder()).append("SELECT * FROM graduands, awards, graduand_awards WHERE graduands._id = graduand_awards.graduand_id AND awards._id = graduand_awards.award_id AND awards._id = ").append(l).append(" ORDER BY ").append("graduands_name").append(" ASC").toString();
        return getWritableDatabase().rawQuery(s, null);
    }

    public Cursor getAllAwards()
    {
        return getWritableDatabase().rawQuery("SELECT  * FROM awards ORDER BY award_name ASC", null);
    }

    public Cursor getAllCourses(long l)
    {
        String s = (new StringBuilder()).append("SELECT * FROM courses WHERE school_id = ").append(l).append(" ORDER BY ").append("course_name").append(" ASC").toString();
        return getWritableDatabase().rawQuery(s, null);
    }

    public Cursor getAllMajors(long l)
    {
        String s = (new StringBuilder()).append("SELECT  * FROM majors WHERE course_id = ").append(l).append(" ORDER BY ").append("majors_name").append(" ASC").toString();
        return getWritableDatabase().rawQuery(s, null);
    }

    public Cursor getAllPostGraduands(long l)
    {
        String s = (new StringBuilder()).append("SELECT * FROM graduands WHERE course_id = ").append(l).append(" ORDER BY ").append("graduands_name").append(" ASC").toString();
        return getWritableDatabase().rawQuery(s, null);
    }

    public Cursor getAllPostGraduandsWithMajors(long l, long l1)
    {
        String s = (new StringBuilder()).append("SELECT * FROM graduands WHERE course_id = ").append(l).append(" AND ").append("majors_id").append(" = ").append(l1).append(" ORDER BY ").append("graduands_name").append(" ASC").toString();
        return getWritableDatabase().rawQuery(s, null);
    }

    public Cursor getAllSchools()
    {
        return getWritableDatabase().rawQuery("SELECT * FROM schools", null);
    }

    public Cursor getAllSpeakers()
    {
        return getWritableDatabase().rawQuery("SELECT * FROM speakers", null);
    }

    public Cursor getAllUnderGraduands(long l)
    {
        String s = (new StringBuilder()).append("SELECT * FROM graduands, honours WHERE graduands.honours_id = honours._id AND course_id = ").append(l).append(" ORDER BY ").append("honours_priority").append(", ").append("graduands_name").append(" ASC").toString();
        return getWritableDatabase().rawQuery(s, null);
    }

    public Cursor getAllUnderGraduandsWithMajors(long l, long l1)
    {
        String s = (new StringBuilder()).append("SELECT * FROM graduands, honours WHERE graduands.honours_id = honours._id AND course_id = ").append(l).append(" AND ").append("majors_id").append(" = ").append(l1).append(" ORDER BY ").append("honours_priority").append(", ").append("graduands_name").append(" ASC").toString();
        return getWritableDatabase().rawQuery(s, null);
    }

    public Cursor getAwardDetails(long l)
    {
        Object obj = (new StringBuilder()).append("SELECT  * FROM awards WHERE _id = ").append(l).toString();
        obj = getWritableDatabase().rawQuery(((String) (obj)), null);
        if (obj != null)
        {
            ((Cursor) (obj)).moveToFirst();
        }
        return ((Cursor) (obj));
    }

    public Cursor getCourseDetails(long l)
    {
        Object obj = (new StringBuilder()).append("SELECT * FROM courses WHERE _id = ").append(l).toString();
        obj = getWritableDatabase().rawQuery(((String) (obj)), null);
        if (obj != null)
        {
            ((Cursor) (obj)).moveToFirst();
        }
        return ((Cursor) (obj));
    }

    public Cursor getSchedule()
    {
        return getWritableDatabase().rawQuery("SELECT * FROM schedule", null);
    }

    public Cursor getScheduleInfo(long l)
    {
        Object obj = (new StringBuilder()).append("SELECT * FROM schedule WHERE _id = ").append(l).toString();
        obj = getWritableDatabase().rawQuery(((String) (obj)), null);
        if (obj != null)
        {
            ((Cursor) (obj)).moveToFirst();
        }
        return ((Cursor) (obj));
    }

    public Cursor getSchoolDetails(long l)
    {
        Object obj = (new StringBuilder()).append("SELECT * FROM schools WHERE _id = ").append(l).toString();
        obj = getWritableDatabase().rawQuery(((String) (obj)), null);
        if (obj != null)
        {
            ((Cursor) (obj)).moveToFirst();
        }
        return ((Cursor) (obj));
    }

    public Cursor gradHonoursCheck(long l)
    {
        String s = (new StringBuilder()).append("SELECT * FROM graduands WHERE course_id = ").append(l).append(" AND ").append("honours_id").append(" = 0").toString();
        return getWritableDatabase().rawQuery(s, null);
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE schedule(_id INTEGER PRIMARY KEY AUTOINCREMENT, task TEXT NOT NULL, desc TEXT NOT NULL, time TEXT NOT NULL, image TEXT NOT NULL);");
        sqlitedatabase.execSQL("CREATE TABLE schools(_id INTEGER PRIMARY KEY AUTOINCREMENT, school_name TEXT NOT NULL, school_image TEXT NOT NULL);");
        sqlitedatabase.execSQL("CREATE TABLE courses(_id INTEGER PRIMARY KEY AUTOINCREMENT, course_name TEXT NOT NULL, school_id TEXT NOT NULL);");
        sqlitedatabase.execSQL("CREATE TABLE graduands(_id INTEGER PRIMARY KEY AUTOINCREMENT, graduands_name TEXT NOT NULL, course_id TEXT NOT NULL, honours_id TEXT NOT NULL, majors_id TEXT NOT NULL, graduand_image TEXT NOT NULL);");
        sqlitedatabase.execSQL("CREATE TABLE speakers(_id INTEGER PRIMARY KEY AUTOINCREMENT, speaker_name TEXT NOT NULL, speaker_title TEXT NOT NULL, speaker_bio TEXT NOT NULL, speaker_image TEXT NOT NULL);");
        sqlitedatabase.execSQL("CREATE TABLE majors(_id INTEGER PRIMARY KEY AUTOINCREMENT, majors_name TEXT NOT NULL, course_id TEXT NOT NULL);");
        sqlitedatabase.execSQL("CREATE TABLE honours(_id INTEGER PRIMARY KEY AUTOINCREMENT, honours_name TEXT NOT NULL, honours_priority TEXT NOT NULL);");
        sqlitedatabase.execSQL("CREATE TABLE awards(_id INTEGER PRIMARY KEY AUTOINCREMENT, award_name TEXT NOT NULL, award_image TEXT NOT NULL);");
        sqlitedatabase.execSQL("CREATE TABLE graduand_awards(_id INTEGER PRIMARY KEY AUTOINCREMENT, graduand_id TEXT NOT NULL, award_id TEXT NOT NULL);");
        Log.d(TAG, "Database tables created");
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS schedule");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS schools");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS courses");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS graduands");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS speakers");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS majors");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS honours");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS awards");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS graduand_awards");
        onCreate(sqlitedatabase);
    }

    public void resetCoursesTable()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("courses", null, null);
        sqlitedatabase.close();
    }

    public void resetGraduandsTable()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("graduands", null, null);
        sqlitedatabase.close();
    }

    public void resetHonoursTable()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("honours", null, null);
        sqlitedatabase.close();
    }

    public void resetMajorsTable()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("majors", null, null);
        sqlitedatabase.close();
    }

    public void resetScheduleTable()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("schedule", null, null);
        sqlitedatabase.close();
    }

    public void resetSchoolsTable()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("schools", null, null);
        sqlitedatabase.close();
    }

    public void resetSpeakersTable()
    {
        SQLiteDatabase sqlitedatabase = getWritableDatabase();
        sqlitedatabase.delete("speakers", null, null);
        sqlitedatabase.close();
    }

}
