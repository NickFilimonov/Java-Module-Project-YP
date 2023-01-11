import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        calcBill();
    }

    /*
    метод calcBill() запрашивает кол-во человек, наименования товаров и их стоимость,
    производит валидацию при введении некорректных чисел,
    подсчитывает сумму цен товаров, делит на количество человек
    */

    public static void calcBill() {


        System.out.println("На какое количество человек необходимо разделить счёт?");
        int amountPerson = getAmountPerson();

        List<Product> products = new ArrayList<>(); // создаем динамический массив объектов, где будут хранится товары и цены на них
        String productName;
        double productPrise = 0.00;
        double sum = 0.00; // переменная, которая будет хранить сумму цен товаров

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название товара. Для завершения введите \"завершить\".");
            productName = scanner.nextLine();
            if (productName.equalsIgnoreCase("Завершить")) {
                break;
            }

            System.out.println("Введите цену товара");
            while (true) {
                try {
                    Scanner scan = new Scanner(System.in);
                    productPrise = scan.nextDouble();
                    while (productPrise <= 0) {
                        System.out.println("Введено некорректное значение. Введите число > 0.");
                        productPrise = scan.nextDouble();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Введено некорректное значение. Введите число > 0.");
                }
            }


            sum += productPrise;

            Product prod = new Product(productName, productPrise); // создаем объект prod, экземпляр класса Product
            products.add(prod); // добавляем объекты в динамический массив
        }

            products.forEach(dish -> { // поочередно извлекаем объекты из массива
                System.out.println(String.format("Товар: %s. Цена: %.2f", dish.name, dish.prise) + " " + getAddition(dish.prise));
            });

            double everyoneHasToPay = sum / amountPerson;

            System.out.println(String.format("Каждый из %d человек должен заплатить: %.2f", amountPerson, everyoneHasToPay) + " " + getAddition(everyoneHasToPay));

    }

    /*
    метод getAmountPerson() возврашает целочисленную переменную amountPerson - кол-во человек,
    на которое необходимо поделить счёт. Проверяет, чтобы число было > 0. Отлавливает ислючение
    InputMismatchException (нечисловой ввод в консоль).
    */
        public static int getAmountPerson() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);

                int amountPerson = scanner.nextInt();
                scanner.nextLine();
                while (amountPerson <= 1) {
                    System.out.println("Введено некорректное значение. Введите целое число > 0.");
                    amountPerson = scanner.nextInt();
                }
                return amountPerson;

            } catch (InputMismatchException e) {
                System.out.println("Введено некорректное значение. Введите целое число > 0.");
            }
        }
    }

    /*
    метод getAddition(double everyoneHasToPay) возврашает строковую переменную "рубль" в правильном падеже
    передаем в метод переменную everyoneHasToPay - сколько должен оплатить каждый
    определяем целочисленный остаток от деления на 10 и подбираем правильный падеж
    */
    public static String getAddition(double everyoneHasToPay) {
        int INTeveryoneHasToPay = (int) everyoneHasToPay;
        int preLastDigit = INTeveryoneHasToPay % 100 / 10;
        if (preLastDigit == 1)
        {
            return "рублей";
        }

        switch (INTeveryoneHasToPay % 10)
        {
            case 1:
                return "рубль";
            case 2:
            case 3:
            case 4:
                return "рубля";
            default:
                return "рублей";
        }
    }
}

class Product {
    String name;
    double prise;

    Product(String name, double prise) {
        this.name = name;
        this.prise = prise;
    }
}


