

package hackerrank.contacts;

import java.util.Scanner;

/**
 * 
 * Check out the resources on the page's right side to learn more about tries. The video tutorial is by Gayle Laakmann McDowell, author of the best-selling interview book Cracking the Coding Interview.
We're going to make our own Contacts application! The application must perform two types of operations:

add name, where  is a string denoting a contact name. This must store  as a new contact in the application.
find partial, where  is a string denoting a partial name to search the application for. It must count the number of contacts starting with  and print the count on a new line.
Given  sequential add and find operations, perform each operation in order.

Input Format

The first line contains a single integer, , denoting the number of operations to perform. 
Each line  of the  subsequent lines contains an operation in one of the two forms defined above.

Constraints

It is guaranteed that  and  contain lowercase English letters only.
The input doesn't have any duplicate  for the  operation.
Output Format

For each find partial operation, print the number of contact names starting with  on a new line.

Sample Input

4
add hack
add hackerrank
find hac
find hak
Sample Output

2
0
Explanation

We perform the following sequence of operations:

Add a contact named hack.
Add a contact named hackerrank.
Find and print the number of contact names beginning with hac. There are currently two contact names in the application and both of them start with hac, so we print  on a new line.
Find and print the number of contact names beginning with hak. There are currently two contact names in the application but neither of them start with hak, so we print  on a new line.
 * @author dwaraknathbs
 *
 */


public class ContactsTrie {

    static class Contacts {
        int wordCount = 0;
        Contacts[] contacts = new Contacts[26];

        public Contacts addCharacter(char ch) {
            if (contacts[ch - 'a'] == null) {
                contacts[ch - 'a'] = new Contacts();
            }
            contacts[ch - 'a'].wordCount++;
            return contacts[ch - 'a'];
        }

        public void addContact(String contact) {
            Contacts temp = this;
            for (int i = 0; i < contact.length(); i++) {
                temp = temp.addCharacter(contact.charAt(i));
            }

        }

        public int searchPartial(String partial) {
            Contacts current = this;
            for (int i = 0; i < partial.length(); i++) {
                current = current.contacts[partial.charAt(i) - 'a'];
                if (current == null)
                    return 0;
            }
            return current.wordCount;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Contacts contacts = new Contacts();
        for (int a0 = 0; a0 < n; a0++) {
            String op = in.next();
            String contact = in.next();

            switch (op) {
                case "add":
                    contacts.addContact(contact);
                    break;
                case "find":
                    System.out.println(contacts.searchPartial(contact));
                    break;

            }
        }
    }
}
