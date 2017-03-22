package hemma.springboot.stock;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.testcontainers.containers.GenericContainer;

public class MySQLContainer extends GenericContainer<MySQLContainer> {
    private final String containerName;
    private final String rootPassword;
    private final String user;
    private final String password;
    private final String databaseName;
    private final int port;

    private MySQLContainer(Builder builder) {
        super("mysql:" + builder.containerVersion);
        this.containerName = builder.containerName;
        this.rootPassword = builder.rootPassword;
        this.user = builder.user;
        this.password = builder.password;
        this.databaseName = builder.databaseName;
        this.port = builder.port;
        setupContainer();
    }

    private void setupContainer() {
        withEnv("MYSQL_ROOT_PASSWORD", rootPassword);
        withEnv("MYSQL_USER", user);
        withEnv("MYSQL_PASSWORD", password);
        withEnv("MYSQL_DATABASE", databaseName);
        withExposedPorts(port);
        setContainerName(containerName);
    }

    public static Builder mysqlcontainer() {
        return new MySQLContainer.Builder();
    }

    public static class Builder {
        private String containerVersion = "latest";
        private String containerName = "mysql_test_container";
        private String rootPassword = "root";
        private String user = "test";
        private String password = "test";
        private String databaseName = "test";
        private int port = 3306;

        Builder containerVersion(String containerVersion) {
            this.containerVersion = containerVersion;
            return this;
        }

        Builder containerName(String containerName) {
            this.containerName = containerName;
            return this;
        }

        Builder rootPassword(String rootPassword) {
            this.rootPassword = rootPassword;
            return this;
        }

        Builder user(String user) {
            this.user = user;
            return this;
        }

        Builder password(String password) {
            this.password = password;
            return this;
        }

        Builder databaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        Builder port(int port) {
            this.port = port;
            return this;
        }

        public MySQLContainer build() {
            return new MySQLContainer(this);
        }
    }

    public MysqlDataSource createRootDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(getContainerIpAddress());

        dataSource.setPort(getMappedPort(port));
        dataSource.setUser("root");
        dataSource.setPassword(rootPassword);
        dataSource.setDatabaseName(databaseName);
        return dataSource;
    }

    public MysqlDataSource createUserDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(getContainerIpAddress());
        dataSource.setPort(getMappedPort(port));
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDatabaseName(databaseName);
        return dataSource;
    }
}
