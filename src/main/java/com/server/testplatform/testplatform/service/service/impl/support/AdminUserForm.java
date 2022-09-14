package com.server.testplatform.testplatform.service.service.impl.support;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.authoption.AuthOptionModel;
import com.server.testplatform.testplatform.model.form.UserFormModel;
import com.server.testplatform.testplatform.model.form.UserQuestModel;
import com.server.testplatform.testplatform.model.form.answer.AnswerQuestModel;
import com.server.testplatform.testplatform.model.settingform.SettingForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdminUserForm{

        public UserFormModel findByIdForm(List<UserFormModel> listUf , long form_id){
            var result = listUf.stream()
                    .filter(x->x.getForm_id() == form_id)
                    .findFirst();

            if(result.isPresent()){
                return result.get();
            }

            return null;
        }

        public UserQuestModel getQuest(List<UserQuestModel> listQuest , long quest_id){
            var result = listQuest
                    .stream()
                    .filter(x->x.getQuest_id() == quest_id)
                    .findFirst();

            if(result.isPresent()){
                return result.get();
            }

            return null;
        }

        //pageQuest - начинается с 0, а id c цифры 1
        public UserQuestModel getQuestToPage(List<UserQuestModel> listQuest , int pageQuest){
            Optional<UserQuestModel> current =  listQuest
                    .stream()
                    .filter(x->x.getNumber_line() == pageQuest)
                    .findFirst();


            if(current.isPresent()){
                return current.get();
            }

            //если мы не нашли возвращаем значение ближайшее выше текущего!
            return nextPageNoEquals(listQuest , pageQuest);
        }

        private UserQuestModel nextPageNoEquals(List<UserQuestModel> listQuest , int pageQuest){
            Optional<UserQuestModel> current =  listQuest
                    .stream()
                    .filter(x->x.getNumber_line() > pageQuest)
                    .findFirst();

            if(current.isPresent()){
                return current.get();
            }


            return null;
        }

        public UserQuestModel searchQuestModel(List<UserQuestModel> listquest , long quest_id){
            Optional<UserQuestModel> qm = listquest.stream()
                    .filter(x->x.getQuest_id() == quest_id)
                    .findFirst();

            if(qm.isPresent()){
                return qm.get();
            }

            return null;
        }

    public List<AnswerQuestModel> changeAnswerStatus(List<AnswerQuestModel> listAnswer , long answer_id , boolean  answer_client){
        List<AnswerQuestModel> listAnswerCurrent = listAnswer.stream()
                .filter(x->x.getAnswer_id() == answer_id)
                .peek(x->x.setCorrectAnswer(answer_client))
                .collect(Collectors.toList());


        if(listAnswerCurrent == null){
            return null;
        }

        return listAnswerCurrent;
    }

        public List<UserQuestModel> sortedToLineNumber(List<UserQuestModel> listQuest){
            Comparator<UserQuestModel> compareByNumberLine = Comparator.comparing(UserQuestModel::getNumber_line);
            Collections.sort(listQuest, compareByNumberLine);

            return listQuest;
        }

        public AuthOptionModel getAuthOptionToFormid(UserFormModel ufm){
            AuthOptionModel model = new AuthOptionModel();
            model.setSelectAdminAuthForm(ufm.getSettingform().getTypeaccess());
            model.setForm_id(ufm.getForm_id());

            return model;
        }
    //typeaccess




        public Boolean isValid(String username , String phone , String email , SettingForm sf){
           int ta =  sf.getTypeaccess();
           return equalsTa(ta ,  username ,  phone ,  email);
            //return false;
        }

        private Boolean equalsTa(int ta , String username , String phone , String email ){
            boolean result ;
            switch (ta) {
                case 0:   // 0 - Доступ к тесту всем только нужно ввести ник
                    result =  isUsername(username);
                    break;
                case 1: // 1 - Доступ к тесту только с введенным e-mail
                    result = isEmail(email);
                    break;
                case 2:// 2 - Доступ к тесту только по телефону
                    result = isPhone(phone);
                    break;
                case 3: // 3 - доступ к тесту по e-mail и телефону
                    result = isAll(email ,  phone);
                    break;
                default:
                    result = false;
                    break;
            }
            return result;
        }

        private boolean isUsername(String username){
            if(username == null)return false;
            if(username.isEmpty() || username.isBlank())return false;

            return true;
        }

        private boolean isPhone(String phone){
            String regex = "^\\d{10}$";
            if(phone == null) return false;
            if(phone.isBlank()) return false;

            return  phone.matches(regex);
        }

        private boolean isEmail(String email){
          Pattern validEmailpattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
          Matcher matcher = validEmailpattern.matcher(email);
          return  matcher.matches();
        }

        private boolean isAll(String email , String phone){
            if(isEmail(email) == true & isPhone(phone) == true){
                return true;
            }

            return false;


        }



}
