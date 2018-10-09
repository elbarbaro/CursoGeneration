package com.barbaro.cursoandroid.db;

public class MessageTable {

    public static final String TABLE_NAME = "messages";

    public static final String ID_FIELD = "id";
    public static final String USER_NAME_FIELD = "username";
    public static final String BODY_FIELD = "body";
    public static final String TIME_FIELD = "time";

    public static final String QUERY =
            "CREATE TABLE " + TABLE_NAME +  " ( " +
                    ID_FIELD + " INT PRIMARY KEY, " +
                    USER_NAME_FIELD + " TEXT, " +
                    BODY_FIELD + " TEXT, " +
                    TIME_FIELD + " TEXT );";

    public static final String[] COLUMNS = {
            ID_FIELD,
            USER_NAME_FIELD,
            BODY_FIELD,
            TIME_FIELD
    };

}
