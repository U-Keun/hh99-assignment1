import java.util.Arrays;
import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        int[] numbers = {0,1,2,3,4,5,6,7,8,9};
        int[] answer = new int[3];
        for (int i=0; i<10; i++) {
            int j = (int)(Math.random() * 10);
            int tmp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = tmp;
        }

        answer = Arrays.copyOf(numbers,3);
        System.out.println("컴퓨터가 숫자를 생성했습니다. 답을 맞춰보세요!");
        Scanner sc = new Scanner(System.in);
        String input = "";
        int count = 0;
        int[] pitch = new int[3];
        int strike;
        int ball;
        do {
            count++;
            strike = 0;
            ball = 0;
            System.out.print(count + "번째 시도 : ");
            input = sc.nextLine();
            if (input.length() != 3) {
                System.out.println("3자리 숫자를 입력해주세요.");
                count--;
                continue;
            }
            for (int i=0; i<3; i++) {
                pitch[i] = Integer.parseInt(input.split("")[i]);
            }
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    if (pitch[i] == answer[j]) {
                        if (i == j) strike++;
                        else ball++;
                        break;
                    }
                }
            }
            if (ball == 3) System.out.println(ball + "B");
            else if (strike == 3) System.out.println(strike + "S");
            else System.out.println(ball + "B" + strike + "S");
        } while(strike < 3);
        System.out.println(count + "번만에 맞히셨습니다.");
        System.out.println("게임을 종료합니다.");
    }
}