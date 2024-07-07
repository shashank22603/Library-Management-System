package org.example;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class idGen {
    protected static Random random = new Random();
    protected static List<String> usedMemberIDs = new ArrayList<>();

    public static String generateUniqueMemberID() {
        while (true) {
            int randomInt = random.nextInt(10000); // Generate a random 4-digit integer
            String memberID = String.format("%04d", randomInt); // Format it as a 4-digit string with leading zeros

            if (!usedMemberIDs.contains(memberID)) {
                usedMemberIDs.add(memberID);
                return memberID;
            }
        }
    }
}

