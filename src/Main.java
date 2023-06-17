import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;



public class Main {
    public static void main(String[] args) {

        try {
            checkData("login", "password", "password");
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Не заполнено какое-то из полей");
        }

    }

    public static void checkData(String login, String password, String confirmPassword) {
        check(login, 0);
        check(password, 1);

        if (!(password.equals(confirmPassword))) {
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }

    public static void check(String checkString, int loginOrPassword) {
        String checkStringLowerCase = checkString.toLowerCase();
        int threshold = 20;
        if (checkString.length() >= threshold && loginOrPassword == 0) {
            throw new WrongLoginException("Превышена разрешенная длина параметра login (20 символов)");
        } else if (checkString.length() >= threshold && loginOrPassword == 1) {
            throw new WrongPasswordException("Превышена разрешенная длина параметра password (20 символов)");
        }
        int flag;
        for (int i = 0; i < checkStringLowerCase.length(); i++) {
            flag = 0;
            for (int j = 0; j < AllowedSymbols.symbols.length; j++) {
                if (checkStringLowerCase.charAt(i) == AllowedSymbols.symbols[j]) {
                    flag = 1;
                }
            }
            if (flag == 0 && loginOrPassword == 0) {
                throw new WrongLoginException("В параметре login используются запрещенные символы");
            } else if (flag == 0 && loginOrPassword == 1) {
                throw new WrongPasswordException("В параметре password используются запрещенные символы");
            }
        }
    }

}
