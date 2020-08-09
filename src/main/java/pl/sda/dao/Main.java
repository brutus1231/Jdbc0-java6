package pl.sda.dao;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
        //departmentDao.printAll();

        departmentDao.printOne(2);
    }
}
