import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CalcBill();
    }

    /*
    метод CalcBill() запрашивает кол-во человек, наименования товаров и их стоимость,
    производит валидацию при введении некорректных чисел,
    подсчитывает сумму цен товаров, делит на количество человек
    */

    public static void CalcBill() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("На какое количество человек необходимо разделить счёт?");
        int amountPerson = scanner.nextInt();
        scanner.nextLine();
        while (amountPerson <= 1) {
            System.out.println("Введено некорректное значение. Попробуйте снова.");
            amountPerson = scanner.nextInt();
            scanner.nextLine(); // без этой строки программа ломается, насколько я понял из-за нажатия на Enter при тестировании
        }

        List<Product> products = new ArrayList<>(); // создаем динамический массив объектов, где будут хранится товары и цены на них
        boolean userInput = true; // переменная для сравнения ввода пользователя со словом "завершить"
        String productName;
        double productPrise;
        double sum = 0.00; // переменная, которая будет хранить сумму цен товаров

        while (userInput) {
            System.out.println("Введите название товара. Для завершения введите \"завершить\"");
            productName = scanner.nextLine();

            if (productName.equalsIgnoreCase("Завершить")) {
                userInput = false;
                break;
            }

            System.out.println("Введите цену товара");
            productPrise = scanner.nextDouble();
            scanner.nextLine();// без этой строки программа ломается, насколько я понял из-за нажатия на Enter при тестировании
            while (productPrise <= 0) {
                System.out.println("Введено некорректное значение. Попробуйте снова.");
                productPrise = scanner.nextDouble();
                scanner.nextLine();// без этой строки программа ломается, насколько я понял из-за нажатия на Enter при тестировании
            }

            sum += productPrise;

            Product prod = new Product(productName, productPrise); // создаем объект prod, экземпляр класса Product
            products.add(prod); // добавляем объекты в диноамический массив
        }

        products.forEach(dish -> { // поочередно извлекаем объекты из массива
            System.out.println(String.format("Товар: %s. Цена: %.2f", dish.name, dish.prise)+ " " + GetAddition(dish.prise));
        });

        double everyoneHasToPay = sum / amountPerson;

        System.out.println(String.format("Каждый из %d человек должен заплатить: %.2f", amountPerson, everyoneHasToPay) + " " + GetAddition(everyoneHasToPay) );
    }
    /*
    метод GetAddition(double everyoneHasToPay) возврашает строковую переменную "рубль" в правильном падеже
    передаем в метод переменную everyoneHasToPay - сколько должен оплатить каждый
    определяем целочисленный остаток от деления на 10 и подбираем правильный падеж
    */
    public static String GetAddition(double everyoneHasToPay) {
        int INTeveryoneHasToPay = (int) everyoneHasToPay;
        String word;
        int preLastDigit = INTeveryoneHasToPay % 100 / 10;
        if (preLastDigit == 1)
        {
            return word = "рублей";
        }

        switch (INTeveryoneHasToPay % 10)
        {
            case 1:
                return word = "рубль";
            case 2:
            case 3:
            case 4:
                return word = "рубля";
            default:
                return word = "рублей";
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


