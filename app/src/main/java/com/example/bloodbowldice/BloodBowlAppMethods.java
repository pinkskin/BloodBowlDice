package com.example.bloodbowldice;

public class BloodBowlAppMethods {
    public static int diceRollHomeFanFactor = diceRoll(2, 6);
    public static int homeFanFactor = 0;
    public static int diceRollAwayFanFactor = diceRoll(2, 6);
    public static int awayFanFactor = 0;

    public static int homeFans = 0;
    public static int awayFans = 0;

    public static int homeFAME = 0;
    public static int awayFAME = 0;

    public static int homeCheerleader = 0;
    public static int awayCheerleader = 0;

    public static int homeAsstCoach = 0;
    public static int awayAsstCoach = 0;

    public BloodBowlAppMethods(int InHomeFanFactor, int InAwayFanFactor, int InHomeCheerleader,
                               int InAwayCheerleader, int InHomeAsstCoach, int InAwayAsstCoach) {
        homeFanFactor = InHomeFanFactor;
        awayFanFactor = InAwayFanFactor;
        homeCheerleader = InHomeCheerleader;
        awayCheerleader = InAwayCheerleader;
        homeAsstCoach = InHomeAsstCoach;
        awayAsstCoach = InAwayAsstCoach;

        homeFans = (diceRollHomeFanFactor + homeFanFactor) * 10000;
        awayFans = (diceRollAwayFanFactor + awayFanFactor) * 10000;

        if (homeFans > awayFans) {
            if (homeFans >= awayFans * 2) {
                homeFAME = 2;
            }
            else {
                homeFAME = 1;
            }
        }
        if (homeFans < awayFans) {
            if (homeFans * 2 <= awayFans) {
                awayFAME = 2;
            }
            else {
                awayFAME = 1;
            }
        }
    }

    public static int diceRoll(int diceCount, int diceType) {
        int rollResult = 0;
        for (int x=0; x<diceCount; x++) {
            rollResult += (int)(Math.random() * diceType) + 1;
        }
        return rollResult;
    }

    // returns BOOL simulate a coin toss
    public static String coinToss() {
        int dieRoll6 = diceRoll(1, 2);

        if (dieRoll6 >= 2) {
            return "Home";
        }
        return "Away";
    }

    // returns STRING weather result
    public static String weatherRoll() {
        int dieRoll2d6 = diceRoll(2, 6);

        if (dieRoll2d6 == 2) {
            return "Sweltering Heat: \nAfter each drive, D6 for each player, on " +
                    "1 not available next drive.";
        }
        if (dieRoll2d6 == 3) {
            return "Very Sunny: \n-1 on all PASS rolls.";
        }
        if (dieRoll2d6 == 11) {
            return "Pouring Rain: \n-1 on all CATCH, INTERCEPT and PICKUP " +
                    "rolls.";
        }
        if (dieRoll2d6 == 12) {
            return "Blizzard: \nGO FOR IT fails on 1–2. Only QUICK and " +
                    "SHORT PASS allowed.";
        }
        else {
            return "Nice: \nPerfect weather for Blood Bowl!";
        }
    }

    // kick roll returns STRING in format of [direction]--[spaces to move]
    public static String kickRoll() {
        String directionRoll = bounceBall();
        int moveRoll = diceRoll(1, 6);
        String kickRollResult = directionRoll +"--"+ moveRoll;

        return kickRollResult;
    }

    // roll on kick off table
    public static String kickOffRoll() {
        int dieRoll2d6 = diceRoll(2, 6);

        if (dieRoll2d6 == 2) {
            return "Get the Ref: Each player gets an extra Bribe.";
        }
        if (dieRoll2d6 == 3) {
            int riotRoll = diceRoll(1, 6);
            String riotRollResult;
            if (riotRoll <= 3) {
                riotRollResult = "both move turn marker forward.";
            }
            else {
                riotRollResult = "both move turn marker backwards.";
            }
            return "Riot: \nIf receiving team's turn is on Turn 7, both move their " +
                    "turn markers back. If receiving team has not taken a turn, " +
                    "both move turn marker forward. Otherwise " + riotRollResult;
        }
        if (dieRoll2d6 == 4) {
            return "Perfect Defense: Kicking team can reorganize team.";
        }
        if (dieRoll2d6 == 5) {
            return "High Kick: \nReceiving can move one player not in a TZ to " +
                    "location where ball will land. MA doesn't matter. Must be " +
                    "unoccupied.";
        }
        if (dieRoll2d6 == 6) {
            int homeD3 = diceRoll(1, 3);
            int awayD3 = diceRoll(1, 3);
            int homeCheerComputation = homeD3 + homeFAME + homeCheerleader;
            int awayCheerComputation = awayD3 + awayFAME + awayCheerleader;
            String cheerComputationString = "Home = " + homeD3 + " + FAME(" + homeFAME + ") + CH(" + homeCheerleader + ")\n" +
                    "Away = " + awayD3 + " + FAME(" + awayFAME + ") + CH(" + awayCheerleader + ")\n\n" +
                    "Cheering Fans:\n";

            if (homeCheerComputation > awayCheerComputation) {
                cheerComputationString += "Home Team gains an extra Team Re-roll this half.";
            }
            if (homeCheerComputation < awayCheerComputation) {
                cheerComputationString += "Away Team gains an extra Team Re-roll this half.";
            }
            if (homeCheerComputation == awayCheerComputation) {
                cheerComputationString += "Home Team and Away Team both gain an extra Team Re-roll this half.";
            }

            return cheerComputationString;
        }
        if (dieRoll2d6 == 7) {
            String kickOffChangeWeather = weatherRoll();
            String niceWeatherResult = "Nice: \nPerfect weather for Blood Bowl!";

            if (kickOffChangeWeather.equals(niceWeatherResult)) {
                return "Changing Weather:\n"
                        + kickOffChangeWeather + " Ball scatters 1 extra square.";
            }
            return "Changing Weather:\n" + kickOffChangeWeather;
        }
        if (dieRoll2d6 == 8) {
            int homeD3 = diceRoll(1, 3);
            int awayD3 = diceRoll(1, 3);
            int homeAsstComputation = homeD3 + homeFAME + homeAsstCoach;
            int awayAsstComputation = awayD3 + awayFAME + awayAsstCoach;
            String asstComputationString = "Home = " + homeD3 + " + FAME(" + homeFAME + ") + ASST(" + homeAsstCoach + ")\n" +
                    "Away = " + awayD3 + " + FAME(" + awayFAME + ") + ASST(" + awayAsstCoach + ")\n\n" +
                    "Brilliant Coaching:\n";

            if (homeAsstComputation > awayAsstComputation) {
                asstComputationString += "Home Team gains an extra Team Re-roll this half.";
            }
            if (homeAsstComputation < awayAsstComputation) {
                asstComputationString += "Away Team gains an extra Team Re-roll this half.";
            }
            if (homeAsstComputation == awayAsstComputation) {
                asstComputationString += "Home Team and Away Team both gain an extra Team Re-roll this half.";
            }

            return asstComputationString;
        }
        if (dieRoll2d6 == 9) {
            return "Quick Snap!: \nAll receiving team players can move 1 square " +
                    "(ignore TZs), may be used to opposing half of pitch.";
        }
        if (dieRoll2d6 == 10) {
            return "Blitz!: \nAny kicking team player not in TZ gets a free turn. A " +
                    "turnover ends bonus turn.";
        }
        if (dieRoll2d6 == 11) {
            int homeD6 = diceRoll(1, 6);
            int awayD6 = diceRoll(1, 6);
            int homeThrowRockComputation = homeD6 + homeFAME;
            int awayThrowRockComputation = awayD6 + awayFAME;
            String throwRockComputationString = "Home = " + homeD6 + " + FAME(" + homeFAME + ")\n" +
                    "Away = " + awayD6 + " + FAME(" + awayFAME + ")\n\n" +
                    "Throw a Rock:\n";
            if (homeThrowRockComputation > awayThrowRockComputation) {
                throwRockComputationString += "Home team's fan throws a rock to player on the opposing team. "
                        + "Choose randomly. Roll on Injury table, no Armor roll needed.";
            }
            if (homeThrowRockComputation < awayThrowRockComputation) {
                throwRockComputationString += "Away team's fan throws a rock to player on the opposing team. "
                        + "Choose randomly. Roll on Injury table, no Armor roll needed.";
            }
            if (homeThrowRockComputation == awayThrowRockComputation) {
                throwRockComputationString += "A fan from each team throws a rock to a player on the "
                        + "opposing team. "
                        + "Choose randomly. Roll on Injury table, no Armor roll needed.";
            }
            return throwRockComputationString;
        }
        if (dieRoll2d6 == 12) {
            return "Pitch Invasion: \nBoth roll D6 + FAME for each opposing " +
                    "player on pitch, on 6+ player is Stunned. 1 before FAME = " +
                    "no effect.";
        }
        return "";
    }

    // returns String. Direction of the ball.
    public static String bounceBall() {
        int dieRoll8 = diceRoll(1, 8);

        if (dieRoll8 == 1) {
            return "(1)NW";
        }
        if (dieRoll8 == 2) {
            return "(2)N";
        }
        if (dieRoll8 == 3) {
            return "(3)NE";
        }
        if (dieRoll8 == 4) {
            return "(4)E";
        }
        if (dieRoll8 == 5) {
            return "(5)SE";
        }
        if (dieRoll8 == 6) {
            return "(6)S";
        }
        if (dieRoll8 == 7) {
            return "(7)SW";
        }
        if (dieRoll8 == 8) {
            return "(8)W";
        }
        return "";
    }

    // roll on injury. returns STRING
    public static String injuryRoll() {
        int diceRoll2d6 = diceRoll(2, 6);
        if (diceRoll2d6 >= 2 && diceRoll2d6 <= 7) {
            return "Injury Result:\n" + diceRoll2d6 + "(Stunned)";
        }
        else if (diceRoll2d6 == 8 || diceRoll2d6 == 9) {
            return "Injury Result:\n" + diceRoll2d6 + "(K.O.)";
        }
        else {
            return "Injury Result:\n" + diceRoll2d6 + "(Casualty)";
        }
    }

    // roll on casualty. returns STRING
    public static String casualtyRoll() {
        String stringD68 = "" + diceRoll(1, 6) + "" + diceRoll(1, 8);
        int intD68 = Integer.parseInt(stringD68);

        if (intD68 >= 11  && intD68 <= 38) {
            return "" + intD68 + "\nBadly Hurt\n(No Effect)";
        }
        else if (intD68 == 41) {
            return "" + intD68 + "\nBroken Ribs\n(Miss Next Game)";
        }
        else if (intD68 == 42) {
            return "" + intD68 + "\nGroin Strain\n(Miss Next Game)";
        }
        else if (intD68 == 43) {
            return "" + intD68 + "\nGouged Eye\n(Miss Next Game)";
        }
        else if (intD68 == 44) {
            return "" + intD68 + "\nBroken Jaw\n(Miss Next Game)";
        }
        else if (intD68 == 45) {
            return "" + intD68 + "\nFractured Arm\n(Miss Next Game)";
        }
        else if (intD68 == 46) {
            return "" + intD68 + "\nFractured Leg\n(Miss Next Game)";
        }
        else if (intD68 == 47) {
            return "" + intD68 + "\nSmashed Hand\n(Miss Next Game)";
        }
        else if (intD68 == 48) {
            return "" + intD68 + "\nPinched Nerve\n(Miss Next Game)";
        }
        else if (intD68 == 51) {
            return "" + intD68 + "\nDamaged Back\n(Niggling Injury)";
        }
        else if (intD68 == 52) {
            return "" + intD68 + "\nSmashed Knee\n(Niggling Injury)";
        }
        else if (intD68 == 53) {
            return "" + intD68 + "\nSmashed Hip\n(-1 MA)";
        }
        else if (intD68 == 54) {
            return "" + intD68 + "\nSmashed Ankle\n(-1 MA)";
        }
        else if (intD68 == 55) {
            return "" + intD68 + "\nSerious Concussion\n(-1 AV)";
        }
        else if (intD68 == 56) {
            return "" + intD68 + "\nFractured Skull\n(-1 AV)";
        }
        else if (intD68 == 57) {
            return "" + intD68 + "\nBroken Neck\n(-1 AG)";
        }
        else if (intD68 == 58) {
            return "" + intD68 + "\nSmashed Collar Bone\n(-1 ST)";
        }
        else {
            return "" + intD68 + "\nDEAD!";
        }
    }
}
