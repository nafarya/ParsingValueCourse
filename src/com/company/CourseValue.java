package com.company;

/**
 * Created by dan on 03.02.16.
 */

    public class CourseValue {
        query query;
        public class query {
            public int count;
            public String created;
            public String lang;
            public Results results;

            public class Results {
                public Rate[] rate;

                public class Rate {
                    public String id;
                    public String name;
                    public String rate;
                    public String date;
                    public String time;
                    public String ask;
                    public String bid;
                }

            }
        }


    }

