package com.server.testplatform.testplatform.variable;

import com.server.testplatform.testplatform.model.settingform.ArrLevelModel;
import com.server.testplatform.testplatform.model.settingform.ArrTypeAccess;

public class ConstSetting {
    //кол-во строчек в 1 странице
    public static int getRowsToPages = 2;
    public static ArrLevelModel[] getLevelArr(){

        ArrLevelModel arrLevelModel1 = new ArrLevelModel();
        ArrLevelModel arrLevelModel2 = new ArrLevelModel();
        ArrLevelModel arrLevelModel3 = new ArrLevelModel();

        arrLevelModel1.setLevelid(0);
        arrLevelModel1.setLevelName("Простой");

        arrLevelModel2.setLevelid(1);
        arrLevelModel2.setLevelName("Нормальный");

        arrLevelModel3.setLevelid(2);
        arrLevelModel3.setLevelName("Сложный");

       return new ArrLevelModel[]{arrLevelModel1 , arrLevelModel2 , arrLevelModel3};
    }

    public static ArrTypeAccess[] getAccessTypeArr(){

        ArrTypeAccess arraccessModel1 = new ArrTypeAccess();
        ArrTypeAccess arraccessModel2 = new ArrTypeAccess();
        ArrTypeAccess arraccessModel3 = new ArrTypeAccess();
        ArrTypeAccess arraccessModel4 = new ArrTypeAccess();


        arraccessModel1.setTcid(0);
        arraccessModel1.setTcname("Только по username");

        arraccessModel2.setTcid(1);
        arraccessModel2.setTcname("Только по email");

        arraccessModel3.setTcid(2);
        arraccessModel3.setTcname("Только по телефону");

        arraccessModel4.setTcid(3);
        arraccessModel4.setTcname("По телефону и email");


        return new ArrTypeAccess[]{arraccessModel1 , arraccessModel2 , arraccessModel3 , arraccessModel4};
    }
}
