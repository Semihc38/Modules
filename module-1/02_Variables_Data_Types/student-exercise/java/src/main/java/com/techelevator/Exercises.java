package com.techelevator;

import java.text.DecimalFormat;

public class Exercises {

	public static void main(String[] args) {

        /*
        1. 4 birds are sitting on a branch. 1 flies away. How many birds are left on
        the branch?
        */

		// ### EXAMPLE:
		int initialNumberOfBirds = 4;
		int birdsThatFlewAway = 1;
		int remainingNumberOfBirds = initialNumberOfBirds - birdsThatFlewAway;

        /*
        2. There are 6 birds and 3 nests. How many more birds are there than
        nests?
        */

		// ### EXAMPLE:
		int numberOfBirds = 6;
		int numberOfNests = 3;
		int numberOfExtraBirds = numberOfBirds - numberOfNests;

        /*
        3. 3 raccoons are playing in the woods. 2 go home to eat dinner. How
        many raccoons are left in the woods?
        */
		int numberOfRacoons = 3;
		int racoonsWhoWentHome = 2;
		int racoonsWhoLeftInWoods=numberOfRacoons-racoonsWhoWentHome;
		System.out.println(racoonsWhoLeftInWoods + " racoon left in the woods.");

        /*
        4. There are 5 flowers and 3 bees. How many less bees than flowers?
        */
		int flowers =5;
		int bees = 3;
		int lessBees = flowers-bees;
		System.out.println(lessBees + " less bees than flowers.");

        /*
        5. 1 lonely pigeon was eating breadcrumbs. Another pigeon came to eat
        breadcrumbs, too. How many pigeons are eating breadcrumbs now?
        */
		int lonelyPigeon=1;
		int anotherPigeon=1;
		int pigeonsEatingBreadCrumbs=lonelyPigeon+anotherPigeon;
		System.out.println(pigeonsEatingBreadCrumbs + " Pigeons are eating breadcrumbs.");

        /*
        6. 3 owls were sitting on the fence. 2 more owls joined them. How many
        owls are on the fence now?
        */
		int owlsOnTheFence = 3;
		int owlsJoined = 2;
		int totalOwlsOnTheFence=owlsOnTheFence + owlsJoined;
		System.out.println("There are " + totalOwlsOnTheFence + " owls on the fence.");

        /*
        7. 2 beavers were working on their home. 1 went for a swim. How many
        beavers are still working on their home?
        */
		int beaversWorking=2;
		int beaversWentForASwim=1;
		int StillWorking=beaversWorking-beaversWentForASwim;
		System.out.println("There are " + StillWorking + " beaver is still working on his home.");

        /*
        8. 2 toucans are sitting on a tree limb. 1 more toucan joins them. How
        many toucans in all?
        */
		int toucansOnTree=2;
		int toucanJoins=1;
		int totalToucansOnTree=toucansOnTree + toucanJoins;
		System.out.println("There are " + totalToucansOnTree + " toucans on a tree in all. ");

        /*
        9. There are 4 squirrels in a tree with 2 nuts. How many more squirrels
        are there than nuts?
        */
		int squirrels=4;
		int nuts=squirrels * 2;
		
		System.out.println("There are " + (nuts - squirrels) + " squirrels than nuts");

        /*
        10. Mrs. Hilt found a quarter, 1 dime, and 2 nickels. How much money did
        she find?
        */
		double quarter = 0.25;
		double dime = 0.10;
		double nickels = 2 * 0.05;
		double totalAmountFound = quarter + dime + nickels;
		System.out.println("Total amount of money + " + totalAmountFound + " cents");

        /*
        11. Mrs. Hilt's favorite first grade classes are baking muffins. Mrs. Brier's
        class bakes 18 muffins, Mrs. MacAdams's class bakes 20 muffins, and
        Mrs. Flannery's class bakes 17 muffins. How many muffins does first
        grade bake in all?
        */
		int brierBakeMuffins=18;
		int macAdamBakeMuffins=20;
		int flanneryBakeMuffins=17;
		int amountOfMuffinsBaked = brierBakeMuffins + macAdamBakeMuffins + flanneryBakeMuffins;
		System.out.println(amountOfMuffinsBaked + " muffins baked.");

        /*
        12. Mrs. Hilt bought a yoyo for 24 cents and a whistle for 14 cents. How
        much did she spend in all for the two toys?
        */
		double yoyo=0.24;
		double whistle=0.14;
		double totalAmountSheSpend = yoyo + whistle;
		System.out.println("She spend on yoyo and whistle in all is "+ totalAmountSheSpend + " cents");

        /*
        13. Mrs. Hilt made 5 Rice Krispie Treats. She used 8 large marshmallows
        and 10 mini marshmallows.How many marshmallows did she use
        altogether?
        */
		int madeRiceKrispie=5;
		int largeMarshmallowUsed=8;
		int miniMarshmallowUsed=10;
		int totalUsageOfMarshmallow = madeRiceKrispie * (largeMarshmallowUsed+miniMarshmallowUsed);
		System.out.println("She used " +  totalUsageOfMarshmallow + " marshmallows for 5 rice krispie. ");

        /*
        14. At Mrs. Hilt's house, there was 29 inches of snow, and Brecknock
        Elementary School received 17 inches of snow. How much more snow
        did Mrs. Hilt's house have?
        */
		int snowAtHiltsHouse=29;
		int snowAtbrecknockSchool=17;
		int inchesSnowMoreAtHiltsHouse = snowAtHiltsHouse - snowAtbrecknockSchool;
		System.out.println("Mrs. Hilt's house has "+ inchesSnowMoreAtHiltsHouse + " inches snow more... ");
		 

        /*
        15. Mrs. Hilt has $10. She spends $3 on a toy truck and $2 on a pencil
        case. How much money does she have left?
        */
		int hiltsMoney=10;
		int toyTruck=3;
		int pencilCase=2;
		int moneyLeft=hiltsMoney-(toyTruck+pencilCase);
		System.out.println("She has $"+ moneyLeft + " left.");

        /*
        16. Josh had 16 marbles in his collection. He lost 7 marbles. How many
        marbles does he have now?
        */
		int marblesCollection=16;
		int lostMarbles=7;
		int totalOfMarbles=marblesCollection-lostMarbles;
		System.out.println("He has "+ totalOfMarbles+" marbles left.");

        /*
        17. Megan has 19 seashells. How many more seashells does she need to
        find to have 25 seashells in her collection?
        */
		int hasSeashells=19;
		int needsCollections=25;
		int needsToFind=needsCollections-hasSeashells;
		System.out.println("She needs to find " + needsToFind + " seashells.");
		

        /*
        18. Brad has 17 balloons. 8 balloons are red and the rest are green. How
        many green balloons does Brad have?
        */
		int HasBalloons=17;
		int redBalloons=8;
		int greenBalloons=HasBalloons-redBalloons;
		System.out.println("Brad has "+ greenBalloons+" green balloons.");

        /*
        19. There are 38 books on the shelf. Marta put 10 more books on the shelf.
        How many books are on the shelf now?
        */
		int booksOnShelf=38;
		int moreBooks=10;
		int booksOnShelfNow = booksOnShelf + moreBooks;
		System.out.println("There are " + booksOnShelfNow + " books on the shelf now.");
		

        /*
        20. A bee has 6 legs. How many legs do 8 bees have?
        */
		int legsOfBee = 6;
		int allBees = 8;
		int legsOfEigthBees=legsOfBee*allBees;
		System.out.println("8 bees have " + legsOfEigthBees + " legs.");

        /*
        21. Mrs. Hilt bought an ice cream cone for 99 cents. How much would 2 ice
        cream cones cost?
        */
		double priceOfIceCream=0.99;
		int twoIceCream=2;
		double costOfTwoIceCream = priceOfIceCream * twoIceCream;
		System.out.println("Two ice creams cost $" + costOfTwoIceCream +".");

        /*
        22. Mrs. Hilt wants to make a border around her garden. She needs 125
        rocks to complete the border. She has 64 rocks. How many more rocks
        does she need to complete the border?
        */
		int rocksHiltHas = 64;
		int rocksCompletesBorder = 125;
		int rocksHiltNeeds = rocksCompletesBorder - rocksHiltHas;
		System.out.println("She needs " + rocksHiltNeeds + " rocks to complete the border._" );
		

        /*
        23. Mrs. Hilt had 38 marbles. She lost 15 of them. How many marbles does
        she have left?
        */
		int hiltHadMarbles=38;
		int amountOfMarblesLost=15;
		int marblesLeft=hiltHadMarbles-amountOfMarblesLost;
		System.out.println("she has "+ marblesLeft+ " marbles left.");
		

        /*
        24. Mrs. Hilt and her sister drove to a concert 78 miles away. They drove 32
        miles and then stopped for gas. How many miles did they have left to drive?
        */
		int milesAwayToConcert=78;
		int driveAndStop=32;
		int milesLeftToConcert = milesAwayToConcert + driveAndStop;
		System.out.println("They have "+milesLeftToConcert+" miles left to drive to concert.");
		

        /*
        25. Mrs. Hilt spent 1 hour and 30 minutes shoveling snow on Saturday
        morning and 45 minutes shoveling snow on Saturday afternoon. How
        much total time did she spend shoveling snow?
        */
		int spendingMinutesforShoveling=135;
		int hour=spendingMinutesforShoveling/60;
		double minute=(spendingMinutesforShoveling%60);
		System.out.println("she spent shovelign snow "+hour+" hours "+ (int)minute+" minutes.");
        /*
        26. Mrs. Hilt bought 6 hot dogs. Each hot dog cost 50 cents. How much
        money did she pay for all of the hot dogs?
        */
		int hiltBought=6;
		double costOfHotDog=0.50;
		double totalPaymentOfHotDog=hiltBought*costOfHotDog;
		System.out.println("She spent "+ (int)totalPaymentOfHotDog +"$ for 6 hot dogs. ");

        /*
        27. Mrs. Hilt has 50 cents. A pencil costs 7 cents. How many pencils can
        she buy with the money she has?
        */ 
		int hiltHas=50;
		int pencilCost=7;
		int canBuyPencil=hiltHas/pencilCost;
		System.out.println("She can buy "+ canBuyPencil+" pencils.");

        /*
        28. Mrs. Hilt saw 33 butterflies. Some of the butterflies were red and others
        were orange. If 20 of the butterflies were orange, how many of them
        were red?
        */
		int hiltSawButterflies=33;
		int orangeButterFlies=20;
		int redButterflies=hiltSawButterflies-orangeButterFlies;
		System.out.println(redButterflies+" of the butterflies were red.");

        /*
        29. Kate gave the clerk $1.00. Her candy cost 54 cents. How much change
        should Kate get back?
        */
		double KateGaveClerk=1.00;
		double costOfCandy=0.54;
		double gettingChange=KateGaveClerk%costOfCandy;
		DecimalFormat decimalFormat=new DecimalFormat();
		System.out.println("Kate should get "+decimalFormat.format(gettingChange)+" cents.");

        /*
        30. Mark has 13 trees in his backyard. If he plants 12 more, how many trees
        will he have?
        */
		int treesInBackyard=13;
		int plantsMoreTree=12;
		int totalTreeInBackyard=treesInBackyard+plantsMoreTree;
		System.out.println("There are "+ totalTreeInBackyard+" trees in his backyard.");

        /*
        31. Joy will see her grandma in two days. How many hours until she sees
        her?
        */
		int oneDay=24;
		int hoursUntilSheSees=oneDay*2;
		System.out.println("Joy will see her grandma until "+hoursUntilSheSees+" hours." );

        /*
        32. Kim has 4 cousins. She wants to give each one 5 pieces of gum. How
        much gum will she need?
        */
		int kimHasCousins=4;
		int numbersOfgum=5;
		int gumsThatSheNeeds=kimHasCousins*numbersOfgum;
		System.out.println("She needs "+gumsThatSheNeeds+" gums." );

        /*
        33. Dan has $3.00. He bought a candy bar for $1.00. How much money is
        left?
        */
		double totalMoney=3.00;
		double priceOfCandy=1.00;
		double hasMoney=totalMoney-priceOfCandy;
		System.out.println("$" + (int)hasMoney + " money left.");

        /*
        34. 5 boats are in the lake. Each boat has 3 people. How many people are
        on boats in the lake?
        */
		int boatsInLake=5;
		int peopleOnboat=3;
		int totalPeople=boatsInLake*peopleOnboat;
		System.out.println("There are "+ totalPeople+" people on the boats in the lake.");

        /*
        35. Ellen had 380 legos, but she lost 57 of them. How many legos does she
        have now?
        */
		int ellenHadLegos=380;
		int sheLostLegos=57;
		int totalLegosSheHas=ellenHadLegos-sheLostLegos;
		System.out.println("She has "+ totalLegosSheHas+ " legos now.");

        /*
        36. Arthur baked 35 muffins. How many more muffins does Arthur have to
        bake to have 83 muffins?
        */
		int bakedMuffins=35;
		int muffinsNeeds=83;
		int needsToBeBaked=muffinsNeeds-bakedMuffins;
		System.out.println("He needs to bake "+ needsToBeBaked+" muffins to have 83 muffins.");

        /*
        37. Willy has 1400 crayons. Lucy has 290 crayons. How many more
        crayons does Willy have then Lucy?
        */
		int crayonsWillyHas=1400;
		int crayonsLucyHas=290;
		int crayonsWillyHasMore=crayonsWillyHas-crayonsLucyHas;
		System.out.println("Willy has "+crayonsWillyHasMore+" more crayons than Lucy.");

        /*
        38. There are 10 stickers on a page. If you have 22 pages of stickers, how
        many stickers do you have?
        */
		int stickersOnPage=10;
		int pages=22;
		int totalStickers=stickersOnPage*pages;
		System.out.println("There are "+ totalStickers+" stickers. ");

        /*
        39. There are 96 cupcakes for 8 children to share. How much will each
        person get if they share the cupcakes equally?
        */
		int totalCupcakes=96;
		int childrenToShare=8;
		int sharingCupcakesEqually=totalCupcakes/childrenToShare;
		System.out.println("Each person will get  "+sharingCupcakesEqually+" cupcakes.");

        /*
        40. She made 47 gingerbread cookies which she will distribute equally in
        tiny glass jars. If each jar is to contain six cookies each, how many
        cookies will not be placed in a jar?
        */
		int totalGingerbreadCookies=47;
		int jarContians=6;
		int cookiesWillNotBePlaced=totalGingerbreadCookies%jarContians;
		System.out.println(cookiesWillNotBePlaced + " cookies will not be placed in a jar.");

        /*
        41. She also prepared 59 croissants which she plans to give to her 8
        neighbors. If each neighbor received and equal number of croissants,
        how many will be left with Marian?
        */
		int totalPreparedCroissant=59;
        int totalCroissanNeighborGet=8;
        int totalCorissantLeft=totalPreparedCroissant%totalCroissanNeighborGet;
        System.out.println(totalCorissantLeft+" will be left with Marian. ");

        /*
        42. Marian also baked oatmeal cookies for her classmates. If she can
        place 12 cookies on a tray at a time, how many trays will she need to
        prepare 276 oatmeal cookies at a time?
        */
        int totalOatmealCookies=276;
        int TrayCanGet=12;
        int totalTraysNeeded=totalOatmealCookies/TrayCanGet;
        System.out.println("She needs "+totalTraysNeeded+" trays for oatmeal cookies.");

        /*
        43. Marian’s friends were coming over that afternoon so she made 480
        bite-sized pretzels. If one serving is equal to 12 pretzels, how many
        servings of bite-sized pretzels was Marian able to prepare?
        */
        int totalPretzelMade=480;
        int oneServing=12;
        int totalServingOfPretzel=totalPretzelMade/oneServing;
        System.out.println("She was able to prepare " +totalServingOfPretzel +" servings of bite-sized pretzels.");
        
        

        /*
        44. Lastly, she baked 53 lemon cupcakes for the children living in the city
        orphanage. If two lemon cupcakes were left at home, how many
        boxes with 3 lemon cupcakes each were given away?
        */
        int totalBakedLemonCupcake=53;
        int totalLemonCupcakeLeft=2;
        int totalLemonCupcake=totalBakedLemonCupcake-totalLemonCupcakeLeft;
        int totalCupcakesGivenAway=totalLemonCupcake/3;
        System.out.println(totalCupcakesGivenAway+" boxes lemon cupcakes were given away.");

        /*
        45. Susie's mom prepared 74 carrot sticks for breakfast. If the carrots
        were served equally to 12 people, how many carrot sticks were left
        uneaten?
        */
        int totalCarrotSticks=74;
        int peopleWhoGetStick=12;
        int totalCarrotSticksLeft=totalCarrotSticks%peopleWhoGetStick;
        System.out.println("There are "+totalCarrotSticksLeft+" carrot sticks left uneaten." );
        

        /*
        46. Susie and her sister gathered all 98 of their teddy bears and placed
        them on the shelves in their bedroom. If every shelf can carry a
        maximum of 7 teddy bears, how many shelves will be filled?
        */
        int totalTeddyBears=98;
        int maxTeddyBearsOnShelf=7;
        int shelvesBeFilled=totalTeddyBears/maxTeddyBearsOnShelf;
        System.out.println(shelvesBeFilled+" shelves will be filled.");
        

        /*
        47. Susie’s mother collected all family pictures and wanted to place all of
        them in an album. If an album can contain 20 pictures, how many
        albums will she need if there are 480 pictures?
        */
        int totalPictureInAlbum=20;
        int totalPictures=480;
        int totalAlbumNeeded=totalPictures/totalPictureInAlbum;
        System.out.println("She needs "+totalAlbumNeeded+" albums." );
        

        /*
        48. Joe, Susie’s brother, collected all 94 trading cards scattered in his
        room and placed them in boxes. If a full box can hold a maximum of 8
        cards, how many boxes were filled and how many cards are there in
        the unfilled box?
        */
        int totalTradingCards=94;
        int totalMaxCard=8;
        int boxesFilled=totalTradingCards/totalMaxCard;
        int boxesUnfilled=totalTradingCards%totalMaxCard;
        System.out.println(boxesFilled + " boxes were filled, "+boxesUnfilled+" boxes were unfilled." );

        /*
        49. Susie’s father repaired the bookshelves in the reading room. If he has
        210 books to be distributed equally on the 10 shelves he repaired,
        how many books will each shelf contain?
        */
        int totalBooks=210;
        int shelves=10;
        int totalBooksOnShelf=totalBooks/shelves;
        System.out.println(totalBooksOnShelf+" books will shelf contain.");

        /*
        50. Cristina baked 17 croissants. If she planned to serve this equally to
        her seven guests, how many will each have?
        */
		int croissantsBaked=17;
		int numberOfGuest=7;
		int amountCroissantsGuestGets = (croissantsBaked / numberOfGuest);
		int amountCroissantsLeft = (croissantsBaked % numberOfGuest);
		System.out.println("Each guest will get "+ amountCroissantsGuestGets + " croissants then " + amountCroissantsLeft+" of them will be left.");

        /*
            CHALLENGE PROBLEMS
        */

        /*
        Bill and Jill are house painters. Bill can paint a 12 x 14 room in 2.15 hours, while Jill averages
        1.90 hours. How long will it take the two painter working together to paint 5 12 x 14 rooms?
        Hint: Calculate the hourly rate for each painter, combine them, and then divide the total walls in feet by the combined hourly rate of the painters.
        Challenge: How many days will it take the pair to paint 623 rooms assuming they work 8 hours a day?.
        */
		double billPaintHour=2.15;
		int roomSize=5*12*14;
		double jillPaintHour=1.90;
		int divided=roomSize/2;
		double billicin=divided/2.15;
		double jillicin=divided/1.90;
		
		
		
		

        /*
        Create and assign variables to hold your first name, last name, and middle initial. Using concatenation,
        build an additional variable to hold your full name in the order of last name, first name, middle initial. The
        last and first names should be separated by a comma followed by a space, and the middle initial must end
        with a period.
        Example: "Hopper, Grace B."
        */
		String firstName="Semih";
		String lastName="Cetin";
		char middleInitial='J';
		String fullName=lastName + ", " + firstName +" " + middleInitial + ".";
		System.out.println(fullName);

        /*
        The distance between New York and Chicago is 800 miles, and the train has already travelled 537 miles.
        What percentage of the trip has been completed?
        Hint: The percent completed is the miles already travelled divided by the total miles.
        Challenge: Display as an integer value between 0 and 100 using casts.
        */
		double milesNyToChicago=800;
		double milesTraveled=537;
		double completionOftrip=(milesTraveled/milesNyToChicago)*100;
		System.out.println((int)completionOftrip+"% of the trip has been completed. ");
		



	}

}
