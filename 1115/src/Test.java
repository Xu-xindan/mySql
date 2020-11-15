import com.mysql.jdbc.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User:XuXindan
 * DATE:2020/11/15
 * TIME:10:13
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法区，并执行该类的静态方法块、静态属性。
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 创建数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=0325&useUnicode=true&characterEncoding=UTF-8");
            System.out.println(connection);

            //创建操作命令对象
            statement = connection.createStatement();

            //执行SQL
            String sql = "select id,name,qq_mail from student";
            resultSet = statement.executeQuery(sql);

            List<Emp> empList = new ArrayList<>();
            //处理结果集（查询操作）
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String qq_mail = resultSet.getString("qq_mail");
                System.out.printf("id=%s,name=%s,qq_mail=%s", id, name, qq_mail);
                System.out.println();
                Emp emp = new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setQq_mail(qq_mail);
                empList.add(emp);
            }
            System.out.println(empList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static class Emp {
        private Integer id;
        private String name;
        private String qq_mail;

        @Override
        public String toString() {
            return "Emp{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", qq_mail='" + qq_mail + '\'' +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQq_mail() {
            return qq_mail;
        }

        public void setQq_mail(String qq_mail) {
            this.qq_mail = qq_mail;
        }
    }
}