package DZ_java.java_DZ_6;

import java.util.*;

public class Task_1 {
    public static void main(String[] args) {
        Notebook[] notebooks = new Notebook[]{
                new Notebook("MSI Katana G76", 17.3, new Integer[]{1920, 1080}, 8, 512, 78900),
                new Notebook("HUAWEI MateBook D15", 15.6, new Integer[]{1920, 1080}, 8, 256, 56900),
                new Notebook("Acer Aspire 5", 15.6, new Integer[]{1920, 1080}, 8, 120, 47900),
                new Notebook("Honor MagicBook 16", 16.1, new Integer[]{1920, 1080}, 16, 512, 42900),
                new Notebook("Digma Eve 10", 10.1, new Integer[]{1366, 768}, 2, 64, 21900)
        };

        Scanner scanner = new Scanner(System.in); // создание объекта сканера

        Notebook lookingForNotebook = getRequestedNotebook(scanner); // получение объекта Ноутбук,содержащего в себе минимальные запрашиваемые характеристики
        System.out.println("Критерии поиска: \n\t" + lookingForNotebook); // печать Ноутбука с искомыми характеристиками
        ArrayList<Notebook> getFiltered = getFiltered(lookingForNotebook, notebooks); // получение списка подходящих моделей
        showFiltered(getFiltered); // печать подходящих моделей
    }

    
    static Notebook getRequestedNotebook(Scanner scanner) {
        System.out.println("Введи критерии для поиска (минимальные значения). Если критерий не важен, то оставь пустой ввод.");
        return new Notebook(
                (String) getValidInput("Бренд >: ", "str", scanner), // можно указать только бренд в любом регистре, поиск в дальнейшем ведется по вхождению подстроки в строку
                (Double) getValidInput("Размер экрана в дюймах >: ", "double", scanner),
                (Integer[]) getValidInput("Разрешение экрана (например, 1920х1080) >: ", "resolution", scanner),
                (Integer) getValidInput("Размер RAM >: ", "int", scanner),
                (Integer) getValidInput("Размер SSD >: ", "int", scanner),
                (Integer) getValidInput("Цена (минимум) >: ", "int", scanner)
        );
    }

    
    static ArrayList<Notebook> getFiltered(Notebook lookingForNotebook, Notebook[] notebooks) {
        ArrayList<Notebook> requestResult = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            if (notebook.compareTo(lookingForNotebook)) {
                requestResult.add(notebook);
            }
        }
        return requestResult;
    }

    
    static void showFiltered(ArrayList<Notebook> requestResult) {
        if (requestResult.size() == 0) {
            System.out.println("По данным критериям нет подходящих моделей ноутбуков.");
        } else {
            System.out.println("Найдены следующие модели: ");
            for (int i = 0; i < requestResult.size(); i++) {
                System.out.printf("\t%d. %s.\n", i + 1, requestResult.get(i).toString());
            }
        }
    }

    
    static Object getValidInput(String msg, String objType, Scanner scanner) {
        while (true) {
            objType = objType.toLowerCase();
            System.out.print("\t" + msg);
            String input = scanner.nextLine();
            if (input.length() == 0 && objType.equals("resolution")) return new Integer[]{null, null};
            if (input.length() == 0) return null;
            switch (objType) {
                case "int" -> {
                    try {
                        int res = Integer.parseInt(input);
                        if (res > 0) return res;
                        else
                            System.out.println("Ошибка: для данной характеристики требуется указать целое положительное число");
                    } catch (Exception e) {
                        System.out.println("Ошибка: для данной характеристики требуется указать целое положительное число");
                    }
                }
                case "double" -> {
                    try {
                        double res = Double.parseDouble(input);
                        if (res > 0) return res;
                        else
                            System.out.println("Ошибка: для данной характеристики требуется указать целое или дробное положительное число");
                    } catch (Exception e) {
                        System.out.println("Ошибка: для данной характеристики требуется указать целое или дробное положительное число");
                    }
                }
                case "resolution" -> {
                    try {
                        String[] resStr = input.toLowerCase().replaceAll(" ", "").replace('x', ' ').replace('х', ' ').split(" ");
                        if (resStr.length == 2) {
                            Integer[] resArr = new Integer[2];
                            resArr[0] = Integer.parseInt(resStr[0]);
                            resArr[1] = Integer.parseInt(resStr[1]);
                            return resArr;
                        } else {
                            System.out.println("Ошибка: разрешение должно быть указано в формате ШИРИНАхДЛИНА, например, 1920x1080.");

                        }
                    } catch (Exception e) {
                        System.out.println("Ошибка: разрешение должно быть указано в формате ШИРИНАхДЛИНА, например, 1920x1080.");
                    }
                }
                case "str" -> {
                    return input;
                }
                default -> System.out.println("Этого не должно было случиться, похоже где-то косяк в objType.");
            }
        }
    }
}