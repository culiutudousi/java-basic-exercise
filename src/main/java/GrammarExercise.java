import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner scan = new Scanner(System.in);
        String firstWordList = scan.nextLine();
        String secondWordList = scan.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        System.out.println(String.join(",", result));
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        List<String> firstWord = Arrays.asList(firstWordList.split(","));
        List<String> secondWord = Arrays.asList(secondWordList.split(","));
        Stream.concat(firstWord.stream(), secondWord.stream()).forEach(s -> {
            if (!s.matches("[a-zA-Z]+")) {
                throw new RuntimeException();
            }
        });
        secondWord = secondWord.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return firstWord.stream()
                .map(String::toUpperCase)
                .filter(secondWord::contains)
                .distinct()
                .map(str -> String.join(" ", str.split("")))
                .sorted()
                .collect(Collectors.toList());
    }
}
