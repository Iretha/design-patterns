package com.smdev.creational.object_pool.example_1;

public class _Main {

    public static void main(String[] args) {
        try {
            Connection conn1 = ConnectionPool.getPool().getConnection(); // new instance created
            Connection conn2 = ConnectionPool.getPool().getConnection(); // new instance created
            Connection conn3 = ConnectionPool.getPool().getConnection(); // new instance created

            conn1.release(); // instance released for reuse

            Connection conn4 = ConnectionPool.getPool().getConnection(); // receives conn1 instance

            conn2.release(); // instance released for reuse
            conn3.release(); // instance released for reuse

            Connection conn5 = ConnectionPool.getPool().getConnection(); // receives conn2 instance
            Connection conn6 = ConnectionPool.getPool().getConnection(); // receives conn3 instance

            Connection conn7 = ConnectionPool.getPool().getConnection(); // new instance created
            Connection conn8 = ConnectionPool.getPool().getConnection(); // new instance created
            Connection conn9 = ConnectionPool.getPool().getConnection(); // throws Exception (max number of connections is exceeded)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
