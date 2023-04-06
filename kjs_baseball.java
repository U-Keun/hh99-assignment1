package weeklyAssignment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class baseball {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] number = new int[10];
        //초기화
        for (int i = 0; i < number.length; i++) {
            number[i] = i;
        }

        //셔플
        int tmp = 0;
        for (int i = 0; i < number.length; i++) {
            int j = (int) (Math.random() * 9);
            tmp = number[i];
            number[i] = number[j];
            number[j] = tmp;
        }

        int[] selectNumber = Arrays.copyOf(number, 3);  //3개 난수 배열
        char[] NumToChar = new char[3];

        for (int i = 0; i < 3; i++) {
            NumToChar[i] = (char)(selectNumber[i] + '0');
        }
        System.out.println("NumtoChar : " + Arrays.toString(NumToChar));
        // 위까지 3개의 난수를 만들고 char[]으로 만들어줌 -> 뒤에서 Hashset으로 인덱스 무관하게 비교


        System.out.println("컴퓨터가 숫자를 생성하였습니다. 서로다른 세자리 숫자로 답을 맞춰보세요");

        for (int i = 1; ; i++) {
            System.out.print(i + "번째 시도 : ");
            String input = sc.nextLine();  //입력
            char[] inputChar = input.toCharArray();  //입력한 숫자를 char 배열로


            int B = 0;  //숫자의 값은 일치하지만 위치가 틀렸으면 B(all)
            int S = 0;  //숫자의 값과 위치가 모두 일치하면 S(trike)

            for (int k = 0; k < NumToChar.length; k++) {
                if (NumToChar[k] == inputChar[k]) {
                    S++;
                }
            }   //인덱스에 따라 모두 일치하는지 확인. -> Strike (S) 개수 확인

            Set<Character> NumToSet = new HashSet<Character>();
            for (char c : NumToChar) {
                NumToSet.add(c);
            }    // 컴퓨터가 만든 난수 3개(NumToChar) char[]를 Set으로 -> else문에서 순서 관계없이 일치하는걸 비교하려고

            if (S == 3) {   //S가 3이면 종료
                System.out.println(i + "번째만에 맞히셨습니다.");
                System.out.println("게임을 종료합니다.");
                break;
            } else {   //S가 3개가 아닐 경우
                for (char c : inputChar) {
                    if (NumToSet.contains(c)) {
                        B++;  // 컴퓨터가 만든 난수 3개 NumToSet과 직접 입력한 inputChar에 인덱스 무관 일치하는지 확인
                    }
                }

                // 발견한 문제 = S면 B로 카운팅이 안되어야함. 근데 인덱스가 같은경우(=S)에 B로도 들어감.
                // B를 카운팅하는 else문 부분에서 인덱스별로 같지 않을 경우 비교가 되어야 하는데
                //이미 위는 만든 코드이므로 if else if문으로 경우의 수를 나누어 해결
                if (S==0 && B==0){
                    System.out.println("0S0B");
                }else if(S==0 && B !=3){  //S는 0, B는 1, 2 -> 인덱스가 일치하지는 않고 || 숫자는 맞는것이 있음
                    System.out.println(S+"S"+B+"B");
                }else if (S==0){ // B가 3인경우 -> 숫자는 3개전부 같지만 인덱스는 다 다른 경우
                    System.out.println(B+"B");  //0S3B
                }else{  //S가 1, 2인 경우 B가 포함되므로 B에서 S를 빼고 출력.
                    System.out.println(S+"S"+(B-S)+"B");
                }

            } //else문 끝
        }//밖 for문끝
    }
}
