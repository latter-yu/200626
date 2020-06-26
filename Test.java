import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main1(String[] args) {
        // Julius Caesar 曾经使用过一种很简单的密码。
        // 对于明文中的每个字符，将它用它字母表中后 5 位对应的字符来代替，这样就得到了密文。
        // 比如字符 A 用 F 来代替。如下是密文和明文中字符的对应关系。
        // 密文 A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
        // 明文 V W X Y Z A B C D E F G H I J K L M N O P Q R S T U
        // 任务是对给定的密文进行解密得到明文。
        // 需要注意的是，密文中出现的字母都是大写字母。
        // 密文中也包括非字母的字符，对这些字符不用进行解码。

        // 输入描述:
        // 输入中的测试数据不超过 100 组。每组数据都有如下的形式，而且各组测试数据之间没有空白的行。
        // 一组测试数据包括三部分：
        // 1. 起始行 - 一行，包括字符串 "START"
        // 2. 密文 - 一行，给出密文，密文不为空，而且其中的字符数不超过 200
        // 3. 结束行 - 一行，包括字符串 "END"
        //在最后一组测试数据之后有一行，包括字符串 "ENDOFINPUT"。
        //输出描述:
        //对每组数据，都有一行输出，给出密文对应的明文。

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("ENDOFINPUT")) {
                break;
            }
            if (input.equals("START")) {
                continue;
            }
            else if (input.equals("END")) {
                continue;
            }
            else {
                for (char s : input.toCharArray()) {
                    // strs.toCharArray(): 将此字符串转换为新的字符数组
                    if (Character.isUpperCase(s)) {
                        // Character.isUpperCase(): 确定指定的字符是否为大写字符
                        System.out.print((char) ('A' + (s - 'A' - 5 + 26) % 26));
                    }else {
                        System.out.print(s);
                    }
                }
                System.out.println();
            }
        }
        scanner.close();
    }

    public static void main2(String[] args) {
        // 小红想买些珠子做一串自己喜欢的珠串。
        // 卖珠子的摊主有很多串五颜六色的珠串，但是不肯把任何一串拆散了卖。
        // 于是小红要你帮忙判断一下，某串珠子里是否包含了全部自己想要的珠子？
        // 如果是，那么告诉她有多少多余的珠子；如果不是，那么告诉她缺了多少珠子。
        // 为方便起见，我们用 [0 - 9]、[a - z]、[A - Z]范围内的字符来表示颜色。

        // 例如，YrR8RrY 是小红想做的珠串:
        // 那么 ppRYYGrrYBR2258 可以买，因为包含了全部她想要的珠子，还多了8颗不需要的珠子；
        // ppRYYGrrYB225 不能买，因为没有黑色珠子，并且少了一颗红色的珠子。

        //输入描述:
        //每个输入包含 1 个测试用例。每个测试用例分别在 2 行中先后给出摊主的珠串和小红想做的珠串，两串都不超过 1000 个珠子。
        //输出描述:
        //如果可以买，则在一行中输出 “Yes” 以及有多少多余的珠子；如果不可以买，则在一行中输出 “No” 以及缺了多少珠子。其间以 1 个空格分隔。

        // 示例:
        // 输入
        // ppRYYGrrYBR2258
        // YrR8RrY
        // 输出
        // Yes 8

        Scanner sc = new Scanner(System.in);
        String strs = sc.nextLine(); // 商家的珠串
        String str = sc.nextLine(); // 小红需要的珠子
        boolean ret = strs.contains(str); // strs 是否包含 str
        int count = 0; // 商家每类珠子含有的个数
        int lastCount = 0; // 商家缺少的珠子个数
        char[] needs = str.toCharArray();
        char[] chars = strs.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        if (ret == true) {
            // 如果珠串中包含小红需要的珠子, 输出珠串中多余的珠子即可.
            System.out.println("Yes" + " " + (strs.length() - str.length()));
        }else {
            // 如果不完全包含, 则计算少了几颗珠子.
            for (int i = 0; i < chars.length; i++) {
                // 遍历 chars, 计算出商家每种珠子的个数, 放入 map.
                if (map.containsKey(chars[i])) {
                    // map.containsKey(): 如果此映射包含指定键的映射，则返回 true
                    count = map.get(chars[i]);
                    // map.get(): 返回到指定键所映射的值，或 null 如果此映射包含该键的映射
                    map.put(chars[i], count + 1);
                }else {
                    map.put(chars[i], 1);
                }
            }
            for (int i = 0; i < needs.length; i++) {
                // 遍历 needs, 计算出小红需要的每种珠子的个数;
                // 若商家有这种珠子, 则 count--;
                // 若 count = 0, 说明商家已无这类珠子, 则 lastCount++.
                if (map.containsKey(needs[i])) {
                    if (map.get(needs[i]) > 0) {
                        count = map.get(needs[i]);
                        map.put(needs[i], count - 1);
                    }else {
                        lastCount++;
                    }
                }else {
                    lastCount++;
                }
            }
            // lastCount 的数量表示商家缺少的珠子个数.
            System.out.println("No" + " " + lastCount);
        }
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
class Plus {
    public ListNode plusAB(ListNode a, ListNode b) {
        // 链表 A + B(有错误) 
        if (a == null && b == null) {
            return null;
        }
        if (a == null && b != null) {
            return b;
        }
        if (b == null && a != null) {
            return a;
        }
        ListNode cur = a;
        ListNode current = b;
        ListNode node = new ListNode(-1);
        ListNode nullNode = new ListNode(-1);
        int prev = 0;
        nullNode.next = a;
        while (a != null && b != null) {
            prev = a.val + b.val;
            a.val = prev % 10;
            cur = a;
            current = b;
            while (prev / 10 > 0) {
                if (prev / 10 > 0 && cur.next == null && current.next == null) {
                    cur.next = node;
                    node.val = 0;
                }
                if (cur.next != null) {
                    cur.next.val = cur.next.val + 1;
                    prev = cur.next.val;
                    cur.next.val = prev % 10;
                    cur = cur.next;
                }
                if (current.next != null && cur.next == null) {
                    current.next.val = current.next.val + 1;
                    prev = current.next.val;
                    current.next.val = prev % 10;
                    current = current.next;
                }
            }
            if (cur.next == null && current.next != null) {
                cur.next = current.next;
                return nullNode.next;
            }
            if (current.next == null) {
                return nullNode.next;
            }else {
                a = a.next;
                b = b.next;
            }
        }
        return nullNode.next;
    }
}