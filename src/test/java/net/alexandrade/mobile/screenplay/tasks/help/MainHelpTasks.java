/*
nuvei.com CONFIDENTIAL

Copyright (c) 2024. All Rights Reserved.

NOTICE: The source code contained or described herein and all documents
related to the source code ("Material") are owned by Nuvei Inc.
or its companies, suppliers or licensors.

Dissemination of this information or reproduction of this material
is strictly forbidden unless prior written permission is obtained
from Nuvei Inc.
*/
package net.alexandrade.mobile.screenplay.tasks.help;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.alexandrade.mobile.features.steps.Hooks;
import net.alexandrade.mobile.screenplay.tasks.commons.LoginAsTasks;
import net.alexandrade.utils.SimpleLogger;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class MainHelpTasks {
    private MainHelpTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final SimpleLogger logger = new SimpleLogger(Hooks.class);

    /**
     * Calculate the super password for the given date and terminalId. Extracted from
     * nuvei_android/app/src/main/java/com/verifone/rootModule/utils/utility/SuperPasswordUtility.kt
     *
     * @param date the date in "yyyyMMdd" format
     * @param terminalId the terminal ID
     * @return the super password
     */
    private static String calculateSuperPassword(String date, String terminalId) {
        // sp starts at 1_000_000
        int sp = 1000000;

        // 2) Rearrange specific characters of the date
        //    dateByteArray = [ date[7], date[2], date[5], date[4], date[6], date[3] ]
        //    Then we map each byte to a char, join them as a String, and parse to int.
        byte[] dateByteArray =
                new byte[] {
                    (byte) date.charAt(7),
                    (byte) date.charAt(2),
                    (byte) date.charAt(5),
                    (byte) date.charAt(4),
                    (byte) date.charAt(6),
                    (byte) date.charAt(3)
                };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append((char) dateByteArray[i]);
        }
        String convertedData = sb.toString();
        int dt = Integer.parseInt(convertedData);

        // 3) Pad the terminalId to length 14 with leading zeros, then take substring(8).
        //    e.g. if terminalId = "1234",
        //    padStart(14,'0') => "00000000001234", then substring(8) => "00001234".
        String paddedTerm = String.format("%14s", terminalId).replace(' ', '0');
        paddedTerm = paddedTerm.substring(8); // keep last 6 chars if original ID had length 6
        int ti = Integer.parseInt(paddedTerm);

        // 4) sp += ti and sp += dt
        sp += ti;
        sp += dt;

        // 5) Convert sp to string, pad to 14, then take substring(8)
        String spStr = String.valueOf(sp);
        spStr = String.format("%14s", spStr).replace(' ', '0');
        return spStr.substring(8);
    }

    private static String getCurrentDateInYYYYmmdd() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the formatter for "yyyyMMdd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Format the current date and return it
        return currentDate.format(formatter);
    }

    public static Performable resolveSuperPassword(Actor actor, String terminalId) {
        String currentDate = getCurrentDateInYYYYmmdd();
        String superPassword = calculateSuperPassword(currentDate, terminalId);

        logger.info(
                "Super password for today '%s' for TID '%s' is: %s"
                        .formatted(currentDate, terminalId, superPassword));

        actor.attemptsTo(LoginAsTasks.fillPassword(actor, superPassword));
        return Task.where("{0} fills super password: " + superPassword);
    }
}
