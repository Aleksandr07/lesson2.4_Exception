import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;



public class Main {
    public static void main(String[] args) {

        try {
            checkData("login", "password", "password");
        } catch (WrongLoginException e) {
            System.out.println("Используются запрещенные символы или превышена разрешенная длина параметра login (20 символов)");
        } catch (WrongPasswordException e) {
            System.out.println("Пароли не совпадают или используются запрещенные символы или превышена разрешенная длина параметра password (20 символов)");
        } catch (NullPointerException e) {
            System.out.println("Не заполнено какое-то из полей");
        }

    }

    public static void checkData(String login, String password, String confirmPassword) {
        check(login, 0);
        check(password, 1);

        if (!(password.equals(confirmPassword))) {
            throw new WrongPasswordException();
        }
    }

    public static void check(String checkString, int loginOrPassword) {
        String checkStringLowerCase = checkString.toLowerCase();
        int threshold = 20;
        if (checkString.length() >= threshold && loginOrPassword == 0) {
            throw new WrongLoginException();
        } else if (checkString.length() >= threshold && loginOrPassword == 1) {
            throw new WrongPasswordException();
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
                throw new WrongLoginException();
            } else if (flag == 0 && loginOrPassword == 1) {
                throw new WrongPasswordException();
            }
        }
    }

}
