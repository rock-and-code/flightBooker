package com.ericlara.flightBooker.Models;

import java.util.List;
//HARDCODED AIRPORT DATA REPOSITORY
//SOURCE: https://en.wikipedia.org/wiki/List_of_airports_in_the_United_States

public class AirportData {
    private AirportData(){}
    public static List<Airport> usAirports = List.of(
            new Airport().name("Birmingham–Shuttlesworth International Airport").city("Birmingham").state("Alabama")
                    .locationId("BHM"),
            new Airport().name("Dothan Regional Airport").city("Dothan").state("Alabama").locationId("DHN"),
            new Airport().name("Huntsville International Airport (Carl T. Jones Field)").city("Huntsville")
                    .state("Alabama").locationId("HSV"),
            new Airport().name("Mobile Regional Airport").city("Mobile").state("Alabama").locationId("MOB"),
            new Airport().name("Montgomery Regional Airport (Dannelly Field)").city("Montgomery").state("Alabama")
                    .locationId("MGM"),
            new Airport().name("Merrill Field").city("Anchorage").state("Alaska").locationId("MRI"),
            new Airport().name("Ted Stevens Anchorage International Airport").city("Anchorage").state("Alaska")
                    .locationId("ANC"),
            new Airport().name("Bethel Airport (also see Bethel Seaplane Base)").city("Bethel").state("Alaska")
                    .locationId("BET"),
            new Airport().name("Merle K. (Mudhole) Smith Airport").city("Cordova").state("Alaska").locationId("CDV"),
            new Airport().name("Deadhorse Airport (Prudhoe Bay Airport)").city("Deadhorse").state("Alaska")
                    .locationId("SCC"),
            new Airport().name("Dillingham Airport").city("Dillingham").state("Alaska").locationId("DLG"),
            new Airport().name("Fairbanks International Airport").city("Fairbanks").state("Alaska").locationId("FAI"),
            new Airport().name("Gustavus Airport").city("Gustavus").state("Alaska").locationId("GST"),
            new Airport().name("Homer Airport").city("Homer").state("Alaska").locationId("HOM"),
            new Airport().name("Juneau International Airport").city("Juneau").state("Alaska").locationId("JNU"),
            new Airport().name("Kenai Municipal Airport").city("Kenai").state("Alaska").locationId("ENA"),
            new Airport().name("Ketchikan International Airport").city("Ketchikan").state("Alaska").locationId("KTN"),
            new Airport().name("King Salmon Airport").city("King Salmon").state("Alaska").locationId("AKN"),
            new Airport().name("Klawock Airport (also see Klawock Seaplane Base)").city("Klawock").state("Alaska")
                    .locationId("AKW"),
            new Airport().name("Kodiak Airport (Benny Benson State Airport)").city("Kodiak").state("Alaska")
                    .locationId("ADQ"),
            new Airport().name("Ralph Wien Memorial Airport").city("Kotzebue").state("Alaska").locationId("OTZ"),
            new Airport().name("Nome Airport").city("Nome").state("Alaska").locationId("OME"),
            new Airport().name("Petersburg James A. Johnson Airport").city("Petersburg").state("Alaska")
                    .locationId("PSG"),
            new Airport().name("Sitka Rocky Gutierrez Airport").city("Sitka").state("Alaska").locationId("SIT"),
            new Airport().name("Unalaska Airport (Tom Madsen/Dutch Harbor Airport)").city("Unalaska").state("Alaska")
                    .locationId("DUT"),
            new Airport().name("Wiley Post–Will Rogers Memorial Airport").city("Utqiaġvik (Barrow)").state("Alaska")
                    .locationId("BRW"),
            new Airport().name("Wrangell Airport (also see Wrangell Seaplane Base)").city("Wrangell").state("Alaska")
                    .locationId("WRG"),
            new Airport().name("Yakutat Airport (also see Yakutat Seaplane Base)").city("Yakutat").state("Alaska")
                    .locationId("YAK"),
            new Airport().name("Flagstaff Pulliam Airport").city("Flagstaff").state("Arizona").locationId("FLG"),
            new Airport().name("Phoenix–Mesa Gateway Airport (formerly Williams AFB)").city("Mesa").state("Arizona")
                    .locationId("IWA"),
            new Airport().name("Page Municipal Airport").city("Page").state("Arizona").locationId("PGA"),
            new Airport().name("Phoenix Sky Harbor International Airport").city("Phoenix").state("Arizona")
                    .locationId("PHX"),
            new Airport().name("Prescott Municipal Airport (Ernest A. Love Field)").city("Prescott").state("Arizona")
                    .locationId("PRC"),
            new Airport().name("Tucson International Airport").city("Tucson").state("Arizona").locationId("TUS"),
            new Airport().name("Yuma International Airport / MCAS Yuma").city("Yuma").state("Arizona")
                    .locationId("NYL"),
            new Airport().name("Northwest Arkansas National Airport").city("Fayetteville").state("Arkansas")
                    .locationId("XNA"),
            new Airport().name("Fort Smith Regional Airport").city("Fort Smith").state("Arkansas").locationId("FSM"),
            new Airport().name("Bill and Hillary Clinton National Airport (Adams Field) (was Little Rock National)")
                    .city("Little Rock").state("Arkansas").locationId("LIT"),
            new Airport().name("Texarkana Regional Airport (Webb Field)").city("Texarkana").state("Arkansas")
                    .locationId("TXK"),
            new Airport().name("California Redwood Coast–Humboldt County Airport").city("Arcata/Eureka")
                    .state("California").locationId("ACV"),
            new Airport().name("Meadows Field").city("Bakersfield").state("California").locationId("BFL"),
            new Airport().name("Hollywood Burbank Airport").city("Burbank").state("California").locationId("BUR"),
            new Airport().name("Fresno Yosemite International Airport").city("Fresno").state("California")
                    .locationId("FAT"),
            new Airport().name("Long Beach Airport (Daugherty Field)").city("Long Beach").state("California")
                    .locationId("LGB"),
            new Airport().name("Los Angeles International Airport").city("Los Angeles").state("California")
                    .locationId("LAX"),
            new Airport().name("Monterey Regional Airport").city("Monterey").state("California").locationId("MRY"),
            new Airport().name("Oakland International Airport").city("Oakland").state("California").locationId("OAK"),
            new Airport().name("Ontario International Airport").city("Ontario").state("California").locationId("ONT"),
            new Airport().name("John Wayne Airport").city("Orange County").state("California").locationId("SNA"),
            new Airport().name("Palm Springs International Airport").city("Palm Springs").state("California")
                    .locationId("PSP"),
            new Airport().name("Redding Municipal Airport").city("Redding").state("California").locationId("RDD"),
            new Airport().name("Sacramento International Airport").city("Sacramento").state("California")
                    .locationId("SMF"),
            new Airport().name("San Diego International Airport (Lindbergh Field)").city("San Diego")
                    .state("California").locationId("SAN"),
            new Airport().name("San Francisco International Airport").city("San Francisco").state("California")
                    .locationId("SFO"),
            new Airport().name("San José Mineta International Airport").city("San Jose").state("California")
                    .locationId("SJC"),
            new Airport().name("San Luis Obispo County Regional Airport (McChesney Field)").city("San Luis Obispo")
                    .state("California").locationId("SBP"),
            new Airport().name("Santa Barbara Municipal Airport (Santa Barbara Airport)").city("Santa Barbara")
                    .state("California").locationId("SBA"),
            new Airport().name("Santa Maria Public Airport (Capt G. Allan Hancock Field)").city("Santa Maria")
                    .state("California").locationId("SMX"),
            new Airport().name("Charles M. Schulz–Sonoma County Airport").city("Santa Rosa").state("California")
                    .locationId("STS"),
            new Airport().name("Stockton Metropolitan Airport").city("Stockton").state("California").locationId("SCK"),
            new Airport().name("San Luis Valley Regional Airport (Bergman Field)").city("Alamosa").state("Colorado")
                    .locationId("ALS"),
            new Airport().name("Aspen/Pitkin County Airport (Sardy Field)").city("Aspen").state("Colorado")
                    .locationId("ASE"),
            new Airport().name("City of Colorado Springs Municipal Airport").city("Colorado Springs").state("Colorado")
                    .locationId("COS"),
            new Airport().name("Denver International Airport").city("Denver").state("Colorado").locationId("DEN"),
            new Airport().name("Durango–La Plata County Airport").city("Durango").state("Colorado").locationId("DRO"),
            new Airport().name("Eagle County Regional Airport").city("Eagle/Vail").state("Colorado").locationId("EGE"),
            new Airport().name("Grand Junction Regional Airport (Walker Field)").city("Grand Junction")
                    .state("Colorado").locationId("GJT"),
            new Airport().name("Gunnison–Crested Butte Regional Airport").city("Gunnison").state("Colorado")
                    .locationId("GUC"),
            new Airport().name("Yampa Valley Airport (Yampa Valley Regional)").city("Hayden").state("Colorado")
                    .locationId("HDN"),
            new Airport().name("Montrose Regional Airport").city("Montrose").state("Colorado").locationId("MTJ"),
            new Airport().name("Bradley International Airport").city("Hartford").state("Connecticut").locationId("BDL"),
            new Airport().name("Tweed-New Haven Airport").city("New Haven").state("Connecticut").locationId("HVN"),
            new Airport().name("Wilmington Airport").city("Wilmington").state("Delaware").locationId("ILG"),
            new Airport().name("Daytona Beach International Airport").city("Daytona Beach").state("Florida")
                    .locationId("DAB"),
            new Airport().name("Fort Lauderdale–Hollywood International Airport").city("Fort Lauderdale")
                    .state("Florida").locationId("FLL"),
            new Airport().name("Southwest Florida International Airport").city("Fort Myers").state("Florida")
                    .locationId("RSW"),
            new Airport().name("Destin–Fort Walton Beach Airport / Eglin Air Force Base").city("Fort Walton Beach")
                    .state("Florida").locationId("VPS"),
            new Airport().name("Gainesville Regional Airport").city("Gainesville").state("Florida").locationId("GNV"),
            new Airport().name("Jacksonville International Airport").city("Jacksonville").state("Florida")
                    .locationId("JAX"),
            new Airport().name("Key West International Airport").city("Key West").state("Florida").locationId("EYW"),
            new Airport().name("Melbourne Orlando International Airport").city("Melbourne").state("Florida")
                    .locationId("MLB"),
            new Airport().name("Miami International Airport").city("Miami").state("Florida").locationId("MIA"),
            new Airport().name("Orlando International Airport").city("Orlando").state("Florida").locationId("MCO"),
            new Airport().name("Northwest Florida Beaches International Airport").city("Panama City").state("Florida")
                    .locationId("ECP"),
            new Airport().name("Pensacola International Airport").city("Pensacola").state("Florida").locationId("PNS"),
            new Airport().name("Punta Gorda Airport").city("Punta Gorda").state("Florida").locationId("PGD"),
            new Airport().name("Orlando Sanford International Airport").city("Sanford").state("Florida")
                    .locationId("SFB"),
            new Airport().name("Sarasota–Bradenton International Airport").city("Sarasota").state("Florida")
                    .locationId("SRQ"),
            new Airport().name("St. Pete–Clearwater International Airport").city("St. Petersburg").state("Florida")
                    .locationId("PIE"),
            new Airport().name("Tallahassee International Airport").city("Tallahassee").state("Florida")
                    .locationId("TLH"),
            new Airport().name("Tampa International Airport").city("Tampa").state("Florida").locationId("TPA"),
            new Airport().name("Palm Beach International Airport").city("West Palm Beach").state("Florida")
                    .locationId("PBI"),
            new Airport().name("Southwest Georgia Regional Airport").city("Albany").state("Georgia").locationId("ABY"),
            new Airport().name("Hartsfield–Jackson Atlanta International Airport").city("Atlanta").state("Georgia")
                    .locationId("ATL"),
            new Airport().name("Augusta Regional Airport (Bush Field)").city("Augusta").state("Georgia")
                    .locationId("AGS"),
            new Airport().name("Brunswick Golden Isles Airport").city("Brunswick").state("Georgia").locationId("BQK"),
            new Airport().name("Columbus Airport").city("Columbus").state("Georgia").locationId("CSG"),
            new Airport().name("Middle Georgia Regional Airport").city("Macon").state("Georgia").locationId("MCN"),
            new Airport().name("Savannah/Hilton Head International Airport").city("Savannah").state("Georgia")
                    .locationId("SAV"),
            new Airport().name("Valdosta Regional Airport").city("Valdosta").state("Georgia").locationId("VLD"),
            new Airport().name("Hilo International Airport").city("Hilo, Hawaii").state("Hawaii").locationId("ITO"),
            new Airport().name("Daniel K. Inouye International Airport").city("Honolulu, Oahu").state("Hawaii")
                    .locationId("HNL"),
            new Airport().name("Kahului Airport").city("Kahului, Maui").state("Hawaii").locationId("OGG"),
            new Airport().name("Ellison Onizuka Kona International Airport at Keahole").city("Kailua-Kona, Hawaii")
                    .state("Hawaii").locationId("KOA"),
            new Airport().name("Molokai Airport (Hoolehua Airport)").city("Kaunakakai, Molokai").state("Hawaii")
                    .locationId("MKK"),
            new Airport().name("Lanai Airport").city("Lanai City, Lanai").state("Hawaii").locationId("LNY"),
            new Airport().name("Lihue Airport").city("Lihue, Kauai").state("Hawaii").locationId("LIH"),
            new Airport().name("Boise Airport (Boise Air Terminal) (Gowen Field)").city("Boise").state("Idaho")
                    .locationId("BOI"),
            new Airport().name("Friedman Memorial Airport").city("Hailey / Sun Valley").state("Idaho")
                    .locationId("SUN"),
            new Airport().name("Idaho Falls Regional Airport (Fanning Field)").city("Idaho Falls").state("Idaho")
                    .locationId("IDA"),
            new Airport().name("Lewiston–Nez Perce County Airport").city("Lewiston").state("Idaho").locationId("LWS"),
            new Airport().name("Pocatello Regional Airport").city("Pocatello").state("Idaho").locationId("PIH"),
            new Airport().name("Magic Valley Regional Airport (Joslin Field)").city("Twin Falls").state("Idaho")
                    .locationId("TWF"),
            new Airport().name("MidAmerica St. Louis Airport / Scott Air Force Base").city("Belleville")
                    .state("Illinois").locationId("BLV"),
            new Airport().name("Central Illinois Regional Airport at Bloomington-Normal").city("Bloomington / Normal")
                    .state("Illinois").locationId("BMI"),
            new Airport().name("University of Illinois - Willard Airport").city("Champaign / Urbana / Savoy")
                    .state("Illinois").locationId("CMI"),
            new Airport().name("Chicago Midway International Airport").city("Chicago").state("Illinois")
                    .locationId("MDW"),
            new Airport().name("Chicago O'Hare International Airport").city("Chicago").state("Illinois")
                    .locationId("ORD"),
            new Airport().name("Veterans Airport of Southern Illinois (Williamson County Regional Airport)")
                    .city("Marion").state("Illinois").locationId("MWA"),
            new Airport().name("Quad City International Airport").city("Moline").state("Illinois").locationId("MLI"),
            new Airport().name("General Downing-Peoria International Airport").city("Peoria").state("Illinois")
                    .locationId("PIA"),
            new Airport().name(
                    "Chicago Rockford International Airport (was Northwest Chicagoland Regional Airport at Rockford)")
                    .city("Rockford").state("Illinois").locationId("RFD"),
            new Airport().name("Abraham Lincoln Capital Airport").city("Springfield").state("Illinois")
                    .locationId("SPI"),
            new Airport().name("Evansville Regional Airport").city("Evansville").state("Indiana").locationId("EVV"),
            new Airport().name("Fort Wayne International Airport").city("Fort Wayne").state("Indiana")
                    .locationId("FWA"),
            new Airport().name("Indianapolis International Airport").city("Indianapolis").state("Indiana")
                    .locationId("IND"),
            new Airport().name("South Bend International Airport").city("South Bend").state("Indiana")
                    .locationId("SBN"),
            new Airport().name("Eastern Iowa Airport").city("Cedar Rapids").state("Iowa").locationId("CID"),
            new Airport().name("Des Moines International Airport").city("Des Moines").state("Iowa").locationId("DSM"),
            new Airport().name("Dubuque Regional Airport").city("Dubuque").state("Iowa").locationId("DBQ"),
            new Airport().name("Sioux Gateway Airport (Brig. General Bud Day Field)").city("Sioux City").state("Iowa")
                    .locationId("SUX"),
            new Airport().name("Waterloo Regional Airport").city("Waterloo").state("Iowa").locationId("ALO"),
            new Airport().name("Garden City Regional Airport").city("Garden City").state("Kansas").locationId("GCK"),
            new Airport().name("Hays Regional Airport").city("Hays").state("Kansas").locationId("HYS"),
            new Airport().name("Manhattan Regional Airport").city("Manhattan").state("Kansas").locationId("MHK"),
            new Airport().name("Salina Regional Airport").city("Salina").state("Kansas").locationId("SLN"),
            new Airport().name("Wichita Dwight D. Eisenhower National Airport (was Wichita Mid-Continent Airport)")
                    .city("Wichita").state("Kansas").locationId("ICT"),
            new Airport().name("Cincinnati/Northern Kentucky International Airport").city("Cincinnati/Covington")
                    .state("Kentucky").locationId("CVG"),
            new Airport().name("Blue Grass Airport").city("Lexington").state("Kentucky").locationId("LEX"),
            new Airport().name("Louisville International Airport (Standiford Field)").city("Louisville")
                    .state("Kentucky").locationId("SDF"),
            new Airport().name("Owensboro–Daviess County Regional Airport").city("Owensboro").state("Kentucky")
                    .locationId("OWB"),
            new Airport().name("Barkley Regional Airport").city("Paducah").state("Kentucky").locationId("PAH"),
            new Airport().name("Alexandria International Airport").city("Alexandria").state("Louisiana")
                    .locationId("AEX"),
            new Airport().name("Baton Rouge Metropolitan Airport (Ryan Field)").city("Baton Rouge").state("Louisiana")
                    .locationId("BTR"),
            new Airport().name("Lafayette Regional Airport (Paul Fournet Field)").city("Lafayette").state("Louisiana")
                    .locationId("LFT"),
            new Airport().name("Lake Charles Regional Airport").city("Lake Charles").state("Louisiana")
                    .locationId("LCH"),
            new Airport().name("Monroe Regional Airport").city("Monroe").state("Louisiana").locationId("MLU"),
            new Airport().name("Louis Armstrong New Orleans International Airport").city("New Orleans")
                    .state("Louisiana").locationId("MSY"),
            new Airport().name("Shreveport Regional Airport").city("Shreveport").state("Louisiana").locationId("SHV"),
            new Airport().name("Bangor International Airport").city("Bangor").state("Maine").locationId("BGR"),
            new Airport().name("Portland International Jetport").city("Portland").state("Maine").locationId("PWM"),
            new Airport().name("Presque Isle International Airport").city("Presque Isle").state("Maine")
                    .locationId("PQI"),
            new Airport().name("Knox County Regional Airport").city("Rockland").state("Maine").locationId("RKD"),
            new Airport().name("Baltimore/Washington International Airport").city("Baltimore").state("Maryland")
                    .locationId("BWI"),
            new Airport().name("Hagerstown Regional Airport (Richard A. Henson Field)").city("Hagerstown")
                    .state("Maryland").locationId("HGR"),
            new Airport().name("Salisbury–Ocean City–Wicomico Regional Airport").city("Salisbury").state("Maryland")
                    .locationId("SBY"),
            new Airport().name("Gen. Edward Lawrence Logan International Airport").city("Boston").state("Massachusetts")
                    .locationId("BOS"),
            new Airport().name("Cape Cod Gateway Airport (Boardman/Polando Field)").city("Hyannis")
                    .state("Massachusetts").locationId("HYA"),
            new Airport().name("Nantucket Memorial Airport").city("Nantucket").state("Massachusetts").locationId("ACK"),
            new Airport().name("Martha's Vineyard Airport").city("Vineyard Haven").state("Massachusetts")
                    .locationId("MVY"),
            new Airport().name("Worcester Regional Airport").city("Worcester").state("Massachusetts").locationId("ORH"),
            new Airport().name("Alpena County Regional Airport").city("Alpena").state("Michigan").locationId("APN"),
            new Airport().name("Detroit Metro Wayne County Airport").city("Detroit").state("Michigan")
                    .locationId("DTW"),
            new Airport().name("Delta County Airport").city("Escanaba").state("Michigan").locationId("ESC"),
            new Airport().name("Bishop International Airport").city("Flint").state("Michigan").locationId("FNT"),
            new Airport().name("Gerald R. Ford International Airport").city("Grand Rapids").state("Michigan")
                    .locationId("GRR"),
            new Airport().name("Houghton County Memorial Airport").city("Hancock").state("Michigan").locationId("CMX"),
            new Airport().name("Ford Airport").city("Iron Mountain").state("Michigan").locationId("IMT"),
            new Airport().name("Kalamazoo/Battle Creek International Airport").city("Kalamazoo").state("Michigan")
                    .locationId("AZO"),
            new Airport().name("Capital Region International Airport (was Lansing Capital City)").city("Lansing")
                    .state("Michigan").locationId("LAN"),
            new Airport().name("Sawyer International Airport").city("Marquette").state("Michigan").locationId("SAW"),
            new Airport().name("Muskegon County Airport").city("Muskegon").state("Michigan").locationId("MKG"),
            new Airport().name("Pellston Regional/Emmet County Airport").city("Pellston").state("Michigan")
                    .locationId("PLN"),
            new Airport().name("MBS International Airport").city("Saginaw").state("Michigan").locationId("MBS"),
            new Airport().name("Chippewa County International Airport").city("Sault Ste. Marie").state("Michigan")
                    .locationId("CIU"),
            new Airport().name("Cherry Capital Airport (was Cherry County Airpark)").city("Traverse City")
                    .state("Michigan").locationId("TVC"),
            new Airport().name("Bemidji Regional Airport").city("Bemidji").state("Minnesota").locationId("BJI"),
            new Airport().name("Brainerd Lakes Regional Airport").city("Brainerd").state("Minnesota").locationId("BRD"),
            new Airport().name("Duluth International Airport").city("Duluth").state("Minnesota").locationId("DLH"),
            new Airport().name("Range Regional Airport (was Chisholm–Hibbing Airport)").city("Hibbing")
                    .state("Minnesota").locationId("HIB"),
            new Airport().name("Minneapolis–St. Paul International/Wold-Chamberlain Airport")
                    .city("Minneapolis-St. Paul").state("Minnesota").locationId("MSP"),
            new Airport().name("Rochester International Airport").city("Rochester").state("Minnesota")
                    .locationId("RST"),
            new Airport().name("St. Cloud Regional Airport").city("St. Cloud").state("Minnesota").locationId("STC"),
            new Airport().name("Golden Triangle Regional Airport").city("Columbus").state("Mississippi")
                    .locationId("GTR"),
            new Airport().name("Gulfport–Biloxi International Airport").city("Gulfport / Biloxi").state("Mississippi")
                    .locationId("GPT"),
            new Airport().name("Hattiesburg–Laurel Regional Airport").city("Hattiesburg / Laurel").state("Mississippi")
                    .locationId("PIB"),
            new Airport().name("Jackson–Medgar Wiley Evers International Airport").city("Jackson").state("Mississippi")
                    .locationId("JAN"),
            new Airport().name("Key Field").city("Meridian").state("Mississippi").locationId("MEI"),
            new Airport().name("Tupelo Regional Airport (C.D. Lemons Field)").city("Tupelo").state("Mississippi")
                    .locationId("TUP"),
            new Airport().name("Branson Airport").city("Branson").state("Missouri").locationId("BBG"),
            new Airport().name("Columbia Regional Airport").city("Columbia").state("Missouri").locationId("COU"),
            new Airport().name("Waynesville-St. Robert Regional Airport (Forney Field)")
                    .city("Fort Leonard Wood / Waynesville").state("Missouri").locationId("TBN"),
            new Airport().name("Joplin Regional Airport").city("Joplin").state("Missouri").locationId("JLN"),
            new Airport().name("Kansas City International Airport (was Mid-Continent International)")
                    .city("Kansas City").state("Missouri").locationId("MCI"),
            new Airport().name("St. Louis Lambert International Airport").city("St. Louis").state("Missouri")
                    .locationId("STL"),
            new Airport().name("Springfield–Branson National Airport").city("Springfield").state("Missouri")
                    .locationId("SGF"),
            new Airport().name("Billings Logan International Airport").city("Billings").state("Montana")
                    .locationId("BIL"),
            new Airport().name("Bozeman Yellowstone International Airport (was Gallatin Field)").city("Bozeman")
                    .state("Montana").locationId("BZN"),
            new Airport().name("Bert Mooney Airport").city("Butte").state("Montana").locationId("BTM"),
            new Airport().name("Great Falls International Airport").city("Great Falls").state("Montana")
                    .locationId("GTF"),
            new Airport().name("Helena Regional Airport").city("Helena").state("Montana").locationId("HLN"),
            new Airport().name("Glacier Park International Airport").city("Kalispell").state("Montana")
                    .locationId("GPI"),
            new Airport().name("Missoula Montana Airport (was Missoula International Airport)").city("Missoula")
                    .state("Montana").locationId("MSO"),
            new Airport().name("Yellowstone Airport").city("West Yellowstone").state("Montana").locationId("WYS"),
            new Airport().name("Central Nebraska Regional Airport").city("Grand Island").state("Nebraska")
                    .locationId("GRI"),
            new Airport().name("Kearney Regional Airport (was Kearney Municipal)").city("Kearney").state("Nebraska")
                    .locationId("EAR"),
            new Airport().name("Lincoln Airport (was Lincoln Municipal)").city("Lincoln").state("Nebraska")
                    .locationId("LNK"),
            new Airport().name("North Platte Regional Airport (Lee Bird Field)").city("North Platte").state("Nebraska")
                    .locationId("LBF"),
            new Airport().name("Eppley Airfield").city("Omaha").state("Nebraska").locationId("OMA"),
            new Airport().name("Western Nebraska Regional Airport (William B. Heilig Field)").city("Scottsbluff")
                    .state("Nebraska").locationId("BFF"),
            new Airport().name("Boulder City Municipal Airport").city("Boulder City").state("Nevada").locationId("BVU"),
            new Airport().name("Elko Regional Airport (J.C. Harris Field)").city("Elko").state("Nevada")
                    .locationId("EKO"),
            new Airport().name("Harry Reid International Airport").city("Las Vegas").state("Nevada").locationId("LAS"),
            new Airport().name("Reno/Tahoe International Airport").city("Reno").state("Nevada").locationId("RNO"),
            new Airport().name("Manchester–Boston Regional Airport").city("Manchester").state("New Hampshire")
                    .locationId("MHT"),
            new Airport().name("Portsmouth International Airport at Pease").city("Portsmouth").state("New Hampshire")
                    .locationId("PSM"),
            new Airport().name("Atlantic City International Airport").city("Atlantic City").state("New Jersey")
                    .locationId("ACY"),
            new Airport().name("Newark Liberty International Airport").city("Newark").state("New Jersey")
                    .locationId("EWR"),
            new Airport().name("Trenton Mercer Airport").city("Trenton").state("New Jersey").locationId("TTN"),
            new Airport().name("Albuquerque International Sunport").city("Albuquerque").state("New Mexico")
                    .locationId("ABQ"),
            new Airport().name("Lea County Regional Airport").city("Hobbs").state("New Mexico").locationId("HOB"),
            new Airport().name("Roswell Air Center").city("Roswell").state("New Mexico").locationId("ROW"),
            new Airport().name("Santa Fe Municipal Airport").city("Santa Fe").state("New Mexico").locationId("SAF"),
            new Airport().name("Albany International Airport").city("Albany").state("New York").locationId("ALB"),
            new Airport().name("Greater Binghamton Airport (Edwin A. Link Field)").city("Binghamton").state("New York")
                    .locationId("BGM"),
            new Airport().name("Buffalo Niagara International Airport").city("Buffalo").state("New York")
                    .locationId("BUF"),
            new Airport().name("Elmira/Corning Regional Airport").city("Elmira / Corning").state("New York")
                    .locationId("ELM"),
            new Airport().name("Ithaca Tompkins International Airport").city("Ithaca").state("New York")
                    .locationId("ITH"),
            new Airport().name("John F. Kennedy International Airport (was New York International Airport)")
                    .city("New York").state("New York").locationId("JFK"),
            new Airport().name("LaGuardia Airport (and Marine Air Terminal)").city("New York").state("New York")
                    .locationId("LGA"),
            new Airport().name("Long Island MacArthur Airport").city("New York / Islip").state("New York")
                    .locationId("ISP"),
            new Airport().name("New York Stewart International Airport").city("Newburgh").state("New York")
                    .locationId("SWF"),
            new Airport().name("Niagara Falls International Airport").city("Niagara Falls").state("New York")
                    .locationId("IAG"),
            new Airport().name("Ogdensburg International Airport").city("Ogdensburg").state("New York")
                    .locationId("OGS"),
            new Airport().name("Plattsburgh International Airport").city("Plattsburgh").state("New York")
                    .locationId("PBG"),
            new Airport().name("Frederick Douglass/Greater Rochester International Airport").city("Rochester")
                    .state("New York").locationId("ROC"),
            new Airport().name("Syracuse Hancock International Airport").city("Syracuse").state("New York")
                    .locationId("SYR"),
            new Airport().name("Watertown International Airport").city("Watertown").state("New York").locationId("ART"),
            new Airport().name("Westchester County Airport").city("White Plains").state("New York").locationId("HPN"),
            new Airport().name("Asheville Regional Airport").city("Asheville").state("North Carolina")
                    .locationId("AVL"),
            new Airport().name("Charlotte Douglas International Airport").city("Charlotte").state("North Carolina")
                    .locationId("CLT"),
            new Airport().name("Concord-Padgett Regional Airport").city("Concord").state("North Carolina")
                    .locationId("JQF"),
            new Airport().name("Fayetteville Regional Airport (Grannis Field)").city("Fayetteville")
                    .state("North Carolina").locationId("FAY"),
            new Airport().name("Piedmont Triad International Airport").city("Greensboro").state("North Carolina")
                    .locationId("GSO"),
            new Airport().name("Pitt–Greenville Airport").city("Greenville").state("North Carolina").locationId("PGV"),
            new Airport().name("Albert J. Ellis Airport").city("Jacksonville").state("North Carolina")
                    .locationId("OAJ"),
            new Airport().name("Coastal Carolina Regional Airport (was Craven County Regional)").city("New Bern")
                    .state("North Carolina").locationId("EWN"),
            new Airport().name("Raleigh–Durham International Airport").city("Raleigh").state("North Carolina")
                    .locationId("RDU"),
            new Airport().name("Wilmington International Airport").city("Wilmington").state("North Carolina")
                    .locationId("ILM"),
            new Airport().name("Bismarck Municipal Airport").city("Bismarck").state("North Dakota").locationId("BIS"),
            new Airport().name("Dickinson Theodore Roosevelt Regional Airport").city("Dickinson").state("North Dakota")
                    .locationId("DIK"),
            new Airport().name("Hector International Airport").city("Fargo").state("North Dakota").locationId("FAR"),
            new Airport().name("Grand Forks International Airport").city("Grand Forks").state("North Dakota")
                    .locationId("GFK"),
            new Airport().name("Jamestown Regional Airport").city("Jamestown").state("North Dakota").locationId("JMS"),
            new Airport().name("Minot International Airport").city("Minot").state("North Dakota").locationId("MOT"),
            new Airport().name("Williston Basin International Airport").city("Williston").state("North Dakota")
                    .locationId("XWA"),
            new Airport().name("Akron-Canton Regional Airport").city("Akron / Canton").state("Ohio").locationId("CAK"),
            new Airport().name("Cleveland Hopkins International Airport").city("Cleveland").state("Ohio")
                    .locationId("CLE"),
            new Airport().name("John Glenn Columbus International Airport").city("Columbus").state("Ohio")
                    .locationId("CMH"),
            new Airport().name("Rickenbacker International Airport").city("Columbus").state("Ohio").locationId("LCK"),
            new Airport().name("James M. Cox Dayton International Airport").city("Dayton").state("Ohio")
                    .locationId("DAY"),
            new Airport().name("Eugene F. Kranz Toledo Express Airport").city("Toledo").state("Ohio").locationId("TOL"),
            new Airport().name("Lawton–Fort Sill Regional Airport").city("Lawton").state("Oklahoma").locationId("LAW"),
            new Airport().name("Will Rogers World Airport").city("Oklahoma City").state("Oklahoma").locationId("OKC"),
            new Airport().name("Stillwater Regional Airport").city("Stillwater").state("Oklahoma").locationId("SWO"),
            new Airport().name("Tulsa International Airport").city("Tulsa").state("Oklahoma").locationId("TUL"),
            new Airport().name("Mahlon Sweet Field").city("Eugene").state("Oregon").locationId("EUG"),
            new Airport().name("Rogue Valley International–Medford Airport").city("Medford").state("Oregon")
                    .locationId("MFR"),
            new Airport().name("Southwest Oregon Regional Airport (was North Bend Municipal)").city("North Bend")
                    .state("Oregon").locationId("OTH"),
            new Airport().name("Portland International Airport").city("Portland").state("Oregon").locationId("PDX"),
            new Airport().name("Roberts Field").city("Redmond").state("Oregon").locationId("RDM"),
            new Airport()
                    .name("Lehigh Valley International Airport (was Allentown–Bethlehem–Easton International Airport)")
                    .city("Allentown").state("Pennsylvania").locationId("ABE"),
            new Airport().name("Erie International Airport (Tom Ridge Field)").city("Erie").state("Pennsylvania")
                    .locationId("ERI"),
            new Airport().name("Harrisburg International Airport").city("Harrisburg").state("Pennsylvania")
                    .locationId("MDT"),
            new Airport().name("Arnold Palmer Regional Airport").city("Latrobe").state("Pennsylvania")
                    .locationId("LBE"),
            new Airport().name("Philadelphia International Airport").city("Philadelphia").state("Pennsylvania")
                    .locationId("PHL"),
            new Airport().name("Pittsburgh International Airport").city("Pittsburgh").state("Pennsylvania")
                    .locationId("PIT"),
            new Airport().name("University Park Airport").city("State College").state("Pennsylvania").locationId("UNV"),
            new Airport().name("Wilkes-Barre/Scranton International Airport").city("Wilkes-Barre / Scranton")
                    .state("Pennsylvania").locationId("AVP"),
            new Airport().name("Block Island State Airport").city("Block Island").state("Rhode Island")
                    .locationId("BID"),
            new Airport().name("Rhode Island T. F. Green International Airport").city("Providence")
                    .state("Rhode Island").locationId("PVD"),
            new Airport().name("Westerly State Airport").city("Westerly").state("Rhode Island").locationId("WST"),
            new Airport().name("Charleston International Airport / Charleston AFB").city("Charleston")
                    .state("South Carolina").locationId("CHS"),
            new Airport().name("Columbia Metropolitan Airport").city("Columbia").state("South Carolina")
                    .locationId("CAE"),
            new Airport().name("Florence Regional Airport").city("Florence").state("South Carolina").locationId("FLO"),
            new Airport().name("Greenville–Spartanburg International Airport (Roger Milliken Field)").city("Greenville")
                    .state("South Carolina").locationId("GSP"),
            new Airport().name("Hilton Head Airport").city("Hilton Head").state("South Carolina").locationId("HXD"),
            new Airport().name("Myrtle Beach International Airport").city("Myrtle Beach").state("South Carolina")
                    .locationId("MYR"),
            new Airport().name("Aberdeen Regional Airport").city("Aberdeen").state("South Dakota").locationId("ABR"),
            new Airport().name("Pierre Regional Airport").city("Pierre").state("South Dakota").locationId("PIR"),
            new Airport().name("Rapid City Regional Airport").city("Rapid City").state("South Dakota")
                    .locationId("RAP"),
            new Airport().name("Joe Foss Field").city("Sioux Falls").state("South Dakota").locationId("FSD"),
            new Airport().name("Watertown Regional Airport").city("Watertown").state("South Dakota").locationId("ATY"),
            new Airport().name("Lovell Field").city("Chattanooga").state("South Dakota").locationId("CHA"),
            new Airport().name("McGhee Tyson Airport").city("Knoxville").state("South Dakota").locationId("TYS"),
            new Airport().name("Memphis International Airport").city("Memphis").state("South Dakota").locationId("MEM"),
            new Airport().name("Nashville International Airport (Berry Field)").city("Nashville").state("South Dakota")
                    .locationId("BNA"),
            new Airport().name("Tri-Cities Airport").city("Tri-Cities").state("South Dakota").locationId("TRI"),
            new Airport().name("Abilene Regional Airport").city("Abilene").state("Texas").locationId("ABI"),
            new Airport().name("Rick Husband Amarillo International Airport").city("Amarillo").state("Texas")
                    .locationId("AMA"),
            new Airport().name("Austin–Bergstrom International Airport").city("Austin").state("Texas")
                    .locationId("AUS"),
            new Airport().name("Jack Brooks Regional Airport (was Southeast Texas Regional)").city("Beaumont")
                    .state("Texas").locationId("BPT"),
            new Airport().name("Brownsville/South Padre Island International Airport").city("Brownsville")
                    .state("Texas").locationId("BRO"),
            new Airport().name("Easterwood Field").city("College Station").state("Texas").locationId("CLL"),
            new Airport().name("Corpus Christi International Airport").city("Corpus Christi").state("Texas")
                    .locationId("CRP"),
            new Airport().name("Dallas Love Field").city("Dallas").state("Texas").locationId("DAL"),
            new Airport().name("Dallas Fort Worth International Airport").city("Dallas").state("Texas")
                    .locationId("DFW"),
            new Airport().name("Del Rio International Airport").city("Del Rio").state("Texas").locationId("DRT"),
            new Airport().name("El Paso International Airport").city("El Paso").state("Texas").locationId("ELP"),
            new Airport().name("Valley International Airport").city("Harlingen").state("Texas").locationId("HRL"),
            new Airport().name("George Bush Intercontinental/Houston Airport").city("Houston").state("Texas")
                    .locationId("IAH"),
            new Airport().name("William P. Hobby Airport").city("Houston").state("Texas").locationId("HOU"),
            new Airport().name("Killeen–Fort Hood Regional Airport / Robert Gray Army Airfield").city("Killeen")
                    .state("Texas").locationId("GRK"),
            new Airport().name("Laredo International Airport").city("Laredo").state("Texas").locationId("LRD"),
            new Airport().name("East Texas Regional Airport").city("Longview").state("Texas").locationId("GGG"),
            new Airport().name("Lubbock Preston Smith International Airport").city("Lubbock").state("Texas")
                    .locationId("LBB"),
            new Airport().name("McAllen Miller International Airport").city("McAllen").state("Texas").locationId("MFE"),
            new Airport().name("Midland International Air and Space Port").city("Midland / Odessa").state("Texas")
                    .locationId("MAF"),
            new Airport().name("San Angelo Regional Airport (Mathis Field)").city("San Angelo").state("Texas")
                    .locationId("SJT"),
            new Airport().name("San Antonio International Airport").city("San Antonio").state("Texas")
                    .locationId("SAT"),
            new Airport().name("Tyler Pounds Regional Airport").city("Tyler").state("Texas").locationId("TYR"),
            new Airport().name("Waco Regional Airport").city("Waco").state("Texas").locationId("ACT"),
            new Airport().name("Wichita Falls Municipal Airport / Sheppard Air Force Base").city("Wichita Falls")
                    .state("Texas").locationId("SPS"),
            new Airport().name("Cedar City Regional Airport").city("Cedar City").state("Utah").locationId("CDC"),
            new Airport().name("Canyonlands Regional Airport").city("Moab").state("Utah").locationId("CNY"),
            new Airport().name("Ogden-Hinckley Airport").city("Ogden").state("Utah").locationId("OGD"),
            new Airport().name("Provo Municipal Airport").city("Provo").state("Utah").locationId("PVU"),
            new Airport().name("St. George Regional Airport").city("St. George").state("Utah").locationId("SGU"),
            new Airport().name("Salt Lake City International Airport").city("Salt Lake City").state("Utah")
                    .locationId("SLC"),
            new Airport().name("Burlington International Airport").city("Burlington").state("Vermont")
                    .locationId("BTV"),
            new Airport().name("Charlottesville–Albemarle Airport").city("Charlottesville").state("Virginia")
                    .locationId("CHO"),
            new Airport().name("Lynchburg Regional Airport (Preston Glenn Field)").city("Lynchburg").state("Virginia")
                    .locationId("LYH"),
            new Airport().name("Newport News/Williamsburg International Airport (Patrick Henry Field)")
                    .city("Newport News").state("Virginia").locationId("PHF"),
            new Airport().name("Norfolk International Airport").city("Norfolk").state("Virginia").locationId("ORF"),
            new Airport().name("Richmond International Airport (Byrd Field)").city("Richmond").state("Virginia")
                    .locationId("RIC"),
            new Airport().name("Roanoke–Blacksburg Regional Airport (Woodrum Field)").city("Roanoke").state("Virginia")
                    .locationId("ROA"),
            new Airport().name("Shenandoah Valley Regional Airport").city("Staunton / Waynesboro / Harrisonburg")
                    .state("Virginia").locationId("SHD"),
            new Airport().name("Ronald Reagan Washington National Airport").city("Washington, D.C. / Arlington")
                    .state("Virginia").locationId("DCA"),
            new Airport().name("Washington Dulles International Airport").city("Washington, D.C. / Dulles / Chantilly")
                    .state("Virginia").locationId("IAD"),
            new Airport().name("Bellingham International Airport").city("Bellingham").state("Washington")
                    .locationId("BLI"),
            new Airport().name("Orcas Island Airport").city("Eastsound").state("Washington").locationId("ORS"),
            new Airport().name("Paine Field (Snohomish County Airport)").city("Everett").state("Washington")
                    .locationId("PAE"),
            new Airport().name("Friday Harbor Airport").city("Friday Harbor").state("Washington").locationId("FHR"),
            new Airport().name("Tri-Cities Airport").city("Pasco").state("Washington").locationId("PSC"),
            new Airport().name("Pullman–Moscow Regional Airport").city("Pullman / Moscow, Idaho").state("Washington")
                    .locationId("PUW"),
            new Airport().name("King County International Airport (Boeing Field)").city("Seattle").state("Washington")
                    .locationId("BFI"),
            new Airport().name("Seattle–Tacoma International Airport").city("Seattle / Tacoma (SeaTac)")
                    .state("Washington").locationId("SEA"),
            new Airport().name("Spokane International Airport (Geiger Field)").city("Spokane").state("Washington")
                    .locationId("GEG"),
            new Airport().name("Walla Walla Regional Airport").city("Walla Walla").state("Washington")
                    .locationId("ALW"),
            new Airport().name("Pangborn Memorial Airport").city("Wenatchee").state("Washington").locationId("EAT"),
            new Airport().name("Yakima Air Terminal (McAllister Field)").city("Yakima").state("Washington")
                    .locationId("YKM"),
            new Airport().name("Yeager Airport").city("Charleston").state("West Virginia").locationId("CRW"),
            new Airport().name("North Central West Virginia Airport (was Harrison-Marion Regional)").city("Clarksburg")
                    .state("West Virginia").locationId("CKB"),
            new Airport().name("Tri-State Airport (Milton J. Ferguson Field)").city("Huntington").state("West Virginia")
                    .locationId("HTS"),
            new Airport().name("Greenbrier Valley Airport").city("Lewisburg").state("West Virginia").locationId("LWB"),
            new Airport().name("Appleton International Airport").city("Appleton").state("Wisconsin").locationId("ATW"),
            new Airport().name("Chippewa Valley Regional Airport").city("Eau Claire").state("Wisconsin")
                    .locationId("EAU"),
            new Airport().name("Green Bay–Austin Straubel International Airport").city("Green Bay").state("Wisconsin")
                    .locationId("GRB"),
            new Airport().name("La Crosse Regional Airport").city("La Crosse").state("Wisconsin").locationId("LSE"),
            new Airport().name("Dane County Regional Airport (Truax Field)").city("Madison").state("Wisconsin")
                    .locationId("MSN"),
            new Airport().name("Milwaukee Mitchell International Airport").city("Milwaukee").state("Wisconsin")
                    .locationId("MKE"),
            new Airport().name("Central Wisconsin Airport").city("Mosinee").state("Wisconsin").locationId("CWA"),
            new Airport().name("Rhinelander–Oneida County Airport").city("Rhinelander").state("Wisconsin")
                    .locationId("RHI"),
            new Airport().name("Casper–Natrona County International Airport").city("Casper").state("Wyoming")
                    .locationId("CPR"),
            new Airport().name("Yellowstone Regional Airport").city("Cody").state("Wyoming").locationId("COD"),
            new Airport().name("Gillette–Campbell County Airport").city("Gillette").state("Wyoming").locationId("GCC"),
            new Airport().name("Jackson Hole Airport").city("Jackson").state("Wyoming").locationId("JAC"),
            new Airport().name("Laramie Regional Airport").city("Laramie").state("Wyoming").locationId("LAR"),
            new Airport().name("Central Wyoming Regional Airport (was Riverton Regional)").city("Riverton")
                    .state("Wyoming").locationId("RIW"),
            new Airport().name("Southwest Wyoming Regional Airport (Rock Springs–Sweetwater County Airport)")
                    .city("Rock Springs").state("Wyoming").locationId("RKS"),
            new Airport().name("Sheridan County Airport").city("Sheridan").state("Wyoming").locationId("SHR"),
            new Airport().name("Antonio B. Won Pat International Airport").city("Agana / Tamuning").state("Guam")
                    .locationId("GUM"),
            new Airport().name("Saipan International Airport (Francisco C. Ada)").city("Obyan, Saipan Island")
                    .state("Northern Marianas").locationId("GSN"),
            new Airport().name("Tinian International Airport (West Tinian)").city("Tinian Island")
                    .state("Northern Marianas").locationId("TNI"),
            new Airport().name("Rafael Hernández Airport").city("Aguadilla").state("Puerto Rico").locationId("BQN"),
            new Airport().name("José Aponte de la Torre Airport").city("Ceiba").state("Puerto Rico").locationId("RVR"),
            new Airport().name("Benjamín Rivera Noriega Airport").city("Culebra").state("Puerto Rico")
                    .locationId("CPX"),
            new Airport().name("Mercedita Airport").city("Ponce").state("Puerto Rico").locationId("PSE"),
            new Airport().name("Fernando Luis Ribas Dominicci Airport (Isla Grande Airport)").city("San Juan")
                    .state("Puerto Rico").locationId("SIG"),
            new Airport().name("Luis Muñoz Marín International Airport").city("San Juan / Carolina")
                    .state("Puerto Rico").locationId("SJU"),
            new Airport().name("Antonio Rivera Rodríguez Airport").city("Vieques").state("Puerto Rico")
                    .locationId("VQS"),
            new Airport().name("Cyril E. King Airport").city("Charlotte Amalie, St. Thomas")
                    .state("U.S. Virgin Islands").locationId("STT"),
            new Airport().name("Henry E. Rohlsen Airport").city("Christiansted, St. Croix").state("U.S. Virgin Islands")
                    .locationId("STX")

    );
}
