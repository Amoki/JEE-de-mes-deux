package dao.fabric;

import dao.instance.RecipesDao;
import dao.instance.UserDao;

public final class DaoFabric {

    // L'utilisation du mot clé volatile permet, en Java version 5 et supérieur,
    // permet d'éviter le cas où "Singleton.instance" est non-nul,
    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe
    // ThreadLocal.
    private static volatile DaoFabric instance = null;

    private static final String DB_HOST = "192.168.56.101";
    private static final String DB_PORT = "5432";
    private static final String DB_NAME = "jee";
    private static final String DB_USER = "jee";
    private static final String DB_PWD = "azerty";

    private DaoFabric() {
        super();
        try {
            // Chargement du Driver, puis établissement de la connexion
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


        /**
         * Méthode permettant de renvoyer une instance de la classe Singleton
         * @return Retourne l'instance du singleton.
         */
        public final static DaoFabric getInstance() {
            // Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
            // d'éviter un appel coûteux à synchronized,
            // une fois que l'instanciation est faite.
            if (DaoFabric.instance == null) {
                // Le mot-clé synchronized sur ce bloc empêche toute instanciation
                // multiple même par différents "threads".
                synchronized (DaoFabric.class) {
                    if (DaoFabric.instance == null) {
                        DaoFabric.instance = new DaoFabric();
                    }
                }
            }
            return DaoFabric.instance;
        }

        public UserDao createUserDao() {
            UserDao userDao = new UserDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD);
            return userDao;
        }
        public RecipesDao createRecipesDao(){
            RecipesDao receipesDao = new RecipesDao(this.DB_HOST,this.DB_PORT,this.DB_NAME,this.DB_USER,this.DB_PWD);
            return receipesDao;
        }

}