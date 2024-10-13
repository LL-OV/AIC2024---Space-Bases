package Astro;

import aic2024.user.*;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;

import java.util.*;


class Astronauta {

	// Patró de moviment dels "Terraformers"
	private final Direction[] movementPatternTR = {
			Direction.NORTH,
			Direction.EAST,
			Direction.SOUTH,
			Direction.WEST,
			Direction.NORTH,
			Direction.NORTHEAST,
			Direction.SOUTHEAST,
			Direction.SOUTHWEST,
			Direction.EAST,
			Direction.NORTHEAST
	};

	// Patro de moviment dels "Exploradors"
	private final Direction[] movementPatternNORD = {
			Direction.NORTHEAST,
			Direction.EAST,
			Direction.NORTHEAST,
			Direction.NORTH,
			Direction.NORTH,
			Direction.NORTHWEST,
			Direction.WEST,
			Direction.NORTHWEST,
			Direction.NORTH,
			Direction.NORTH,
			Direction.NORTHEAST,
			Direction.EAST,
			Direction.NORTHEAST,
			Direction.NORTH
	};
	private final Direction[] movementPatternNORDEST = {
			Direction.NORTHEAST,
			Direction.EAST,
			Direction.NORTHEAST,
			Direction.EAST,
			Direction.NORTHEAST,
			Direction.NORTH,
			Direction.NORTHEAST,
			Direction.NORTH,
			Direction.NORTHEAST,
			Direction.EAST,
			Direction.NORTHEAST,
			Direction.EAST,
	};
	private final Direction[] movementPatternEST = {
			Direction.SOUTHEAST,
			Direction.SOUTH,
			Direction.SOUTHEAST,
			Direction.EAST,
			Direction.EAST,
			Direction.NORTHEAST,
			Direction.NORTH,
			Direction.NORTHEAST,
			Direction.EAST,
			Direction.EAST,
			Direction.SOUTHEAST,
			Direction.SOUTH,
			Direction.SOUTHEAST,
			Direction.EAST,
	};
	private final Direction[] movementPatternSUDEST = {
			Direction.SOUTHEAST,
			Direction.EAST,
			Direction.SOUTHEAST,
			Direction.EAST,
			Direction.SOUTHEAST,
			Direction.SOUTH,
			Direction.SOUTHEAST,
			Direction.SOUTH,
			Direction.SOUTHEAST,
			Direction.EAST,
			Direction.SOUTHEAST,
			Direction.EAST,
	};
	private final Direction[] movementPatternSUD = {
			Direction.SOUTHEAST,
			Direction.EAST,
			Direction.SOUTHEAST,
			Direction.SOUTH,
			Direction.SOUTH,
			Direction.SOUTHWEST,
			Direction.WEST,
			Direction.SOUTHWEST,
			Direction.SOUTH,
			Direction.SOUTH,
			Direction.SOUTHEAST,
			Direction.EAST,
			Direction.SOUTHEAST,
			Direction.SOUTH,
	};
	private final Direction[] movementPatternSUDOEST = {
			Direction.SOUTHWEST,
			Direction.WEST,
			Direction.SOUTHWEST,
			Direction.WEST,
			Direction.SOUTHWEST,
			Direction.SOUTH,
			Direction.SOUTHWEST,
			Direction.SOUTH,
			Direction.SOUTHWEST,
			Direction.WEST,
			Direction.SOUTHWEST,
			Direction.WEST,
	};
	private final Direction[] movementPatternOEST = {
			Direction.SOUTHWEST,
			Direction.SOUTH,
			Direction.SOUTHWEST,
			Direction.WEST,
			Direction.WEST,
			Direction.NORTHWEST,
			Direction.NORTH,
			Direction.SOUTHWEST,
			Direction.WEST,
			Direction.WEST,
			Direction.SOUTHEAST,
			Direction.SOUTH,
			Direction.SOUTHEAST,
			Direction.WEST,
	};
	private final Direction[] movementPatternNORDOEST = {
			Direction.NORTHWEST,
			Direction.WEST,
			Direction.NORTHWEST,
			Direction.WEST,
			Direction.NORTHWEST,
			Direction.NORTH,
			Direction.NORTHWEST,
			Direction.NORTH,
			Direction.NORTHWEST,
			Direction.WEST,
			Direction.NORTHWEST,
			Direction.WEST,
	};



	//------------------------------------------------------------

	// TIPOS D'ASTRONAUTES
	// NULL
	// EXPLORADOR NORD = 1
	// EXPLORADOR EST = 2
	// EXPLORADOR SUD = 3
	// EXPLORADOR NORDEST = 4
	// EXPLORADOR SUDEST = 5
	// TERRAFORMER = 9

	// VARIABLES
	// Astronaute
	private int tipus;
	private final int oxigen;
	private CarePackage carePackage;
	// Contador d'astronautes



	// Especificacions dels astronautes
	public Astronauta(int tipus, int oxigen, CarePackage carePackage) {
		this.tipus = tipus;
		this.oxigen = oxigen;
		this.carePackage = carePackage;
	}


	// SETTERS I GETTERS

	//Moviment dels astronautes
	public Direction[] getMovementPatternNORD() {
		return movementPatternNORD;
	}
	public Direction[] getMovementPatternNORDEST() {
		return movementPatternNORDEST;
	}
	public Direction[] getMovementPatternEST() {
		return movementPatternEST;
	}
	public Direction[] getMovementPatternSUDEST() {
		return movementPatternSUDEST;
	}
	public Direction[] getMovementPatternSUD() {
		return movementPatternSUD;
	}
	public Direction[] getMovementPatternSUDOEST() {
		return movementPatternSUDOEST;
	}
	public Direction[] getMovementPatternOEST() {
		return movementPatternOEST;
	}
	public Direction[] getMovementPatternNORDOEST() {
		return movementPatternNORDOEST;
	}

	// Llista d'astronautes a crear
	public void setTipus(int valor) {
		tipus = valor;
	}
	public int getOxigen() {
		return oxigen;
	}
	public int getTipus() {
		return tipus;
	}
	public CarePackage getCarePackage() {
		return carePackage;
	}

	public void setCarePackage(CarePackage cp) {
		carePackage = cp;
	}



	// METÒDES
	// Quina acció a de fer l'astronaute si està equipat amb un CarePackage
	public void accionsPerPackage(UnitController uc, String packageName, Direction[] directions) {
		switch(packageName){
			case "DOME":
				// Per a construir un DOME a d'estar a una certa distancia d'un altre dome o base
				if(!uc.isDomed(uc.getLocation())) {
					if(uc.canPerformAction(ActionType.BUILD_DOME,Direction.ZERO, 0)){
						uc.performAction(ActionType.BUILD_DOME, Direction.ZERO, 0);
					}
				}

				break;
			case "HYPERJUMP":

				Location hqlocation = uc.getParent().getLocation();
				int visionRangeMeitat = uc.getParent().getType().getVisionRange() /2;

				boolean construit = false;

				if(uc.getLocation().distanceSquared(hqlocation) > (visionRangeMeitat)) {
					for (Direction direction : directions) {
						Location adjLocation = uc.getLocation().add(direction);
						if (uc.senseObjectAtLocation(adjLocation) == MapObject.WATER) {
							if (uc.canPerformAction(ActionType.BUILD_HYPERJUMP, Direction.ZERO, 0)) {
								uc.performAction(ActionType.BUILD_HYPERJUMP, Direction.ZERO, 0);
								construit = true;
								break;
							}

						}
					}
				}

				break;
			case "SETTLEMENT":
				// Per a construir un settlement a d'estar a una certa distrancia de les bases HQ
				// Mirar que hi hagi una diferencia de rang entre les HQ i les Settlement
				Location HQlocation = uc.getParent().getLocation();
				int visionRange = uc.getParent().getType().getVisionRange();
				if(uc.getLocation().distanceSquared(HQlocation) > visionRange){
					if(uc.canPerformAction(ActionType.BUILD_SETTLEMENT, Direction.NORTH, 0)){
						uc.performAction(ActionType.BUILD_SETTLEMENT, Direction.NORTH, 0);
					}
				}
				else{
					// Que es vagin movent cap alguna direcció que cada vegada estigui més llunyana a la base central
					Direction direction = getDireccióMésLlunyana(uc, directions);
					uc.performAction(ActionType.MOVE, direction, 0);

				}
				break;

			default:
				break;
		}

	}


	private Direction getDireccióMésLlunyana(UnitController uc, Direction[] directions) {
		// Calcula la direcció de la HQ pare
		Location HQlocation = uc.getParent().getLocation();
		int visionRange = uc.getParent().getType().getVisionRange();

		// Variable a tornar a la direcció que l'astronauta, s'ha de moure
		Direction directionLlunyana = Direction.NORTH;

		// Obtenir la direcció oposada
		Direction directionOposada = uc.getLocation().directionTo(HQlocation).opposite();

		// Mirar si es pot anar cap a la direcció oposada
		if(uc.canPerformAction(ActionType.MOVE, directionOposada, 0)){
			directionLlunyana = directionOposada;
		}
		else {
			// No pot intentar anar a una direcció més llunyada aleatoria

			int distancia = 0;

			for(Direction direction: directions) {
				Location location = uc.getLocation().add(direction);
				int newDistancia = location.distanceSquared(HQlocation);

				if((newDistancia > distancia)&&(uc.canPerformAction(ActionType.MOVE, direction, 0))){
					distancia = newDistancia;
					directionLlunyana = direction;
				}

			}


		}

		return directionLlunyana;

	}

}


public class UnitPlayer {

	final Direction[] directions = Direction.values();

	// METÒDES
	// Emmagatzemar quantitat d'asctronautes
	int[] astronautaTypeCounts = new int[9];
	private int getNextAstronautaType(UnitController uc) {
		Location baseLocation = uc.getLocation();

		// Calculem en quina zona està la base
		int zoneX = baseLocation.x / (uc.getMapWidth() / 3);
		int zoneY = baseLocation.y / (uc.getMapHeight() / 3);

		List<Integer> availableTypes = getIntegers(uc, baseLocation);

		// Comprovem si el contador ha arribat al màxim per a tots els tipus d'astronautes
		if (isMaxCountReached()) {
			resetAstronautaTypeCounts();
		}

		// Reset el contador de tipus d'astronautes si no hi ha cap tipus d'astronauta disponible
		if (availableTypes.isEmpty()) {
			resetAstronautaTypeCounts();
			availableTypes = getIntegers(uc, baseLocation);
		}

		// Random AstronautaType
		int nextType = availableTypes.get((int) (Math.random() * availableTypes.size()));

		// Incrementem el contador per al tipus escollit
		astronautaTypeCounts[nextType - 1]++;

		return nextType;
	}

	private boolean isMaxCountReached() {
		for (int count : astronautaTypeCounts) {
			if (count < 2) {
				return false;
			}
		}
		return true;
	}

	private void resetAstronautaTypeCounts() {
		for (int i = 0; i < 9; i++) {
			astronautaTypeCounts[i] = 0;
		}
	}

	private List<Integer> getIntegers(UnitController uc, Location baseLocation) {
		int zoneX = baseLocation.x / (uc.getMapWidth() / 3);
		int zoneY = baseLocation.y / (uc.getMapHeight() / 3);

		// Astronautes que es poden crear en cada zona

		int[][] zoneAstronautaType = {
				{2, 3, 6, 9}, // (0,0)
				{2, 3, 4, 6, 7, 9}, // (0,1)
				{3, 4, 7, 9}, // (0,2)
				{1, 2, 3, 5, 6, 9}, // (1,0)
				{1, 2, 3, 4, 5, 6, 7, 8, 9}, // (1,1)
				{1, 3, 4, 7, 8, 9}, // (1,2)
				{1, 2, 5, 9}, // (2,0)
				{1, 2, 4, 5, 8, 9}, // (2,1)
				{1, 4, 8, 9} // (2,2)
		};



		// Llista d'astronautes disponible
		List<Integer> availableTypes = new ArrayList<>();
		for (int type : zoneAstronautaType[zoneY * 3 + zoneX]) {
			if (astronautaTypeCounts[type - 1] < 2) {
				availableTypes.add(type);
			}
		}
		return availableTypes;
	}

	// CarePackage
	private CarePackage getPackageType(UnitController uc, Astronauta astro) {
		StructureInfo structureInfo = uc.getStructureInfo(); // 1 energy
		CarePackage cp = null;

		// Contador de CarePackage
		int reinforcedSuitCount = structureInfo.getCarePackagesOfType(CarePackage.REINFORCED_SUIT);	// 1 energy
		int survivalKitCount = structureInfo.getCarePackagesOfType(CarePackage.SURVIVAL_KIT);		// 1 energy
		int settlementCount = structureInfo.getCarePackagesOfType(CarePackage.SETTLEMENT);			// 1 energy
		int domeCount = structureInfo.getCarePackagesOfType(CarePackage.DOME);						// 1 energy
		int hyperjumpCount = structureInfo.getCarePackagesOfType(CarePackage.HYPERJUMP);			// 1 energy

		if (settlementCount >= 1) {
			cp = CarePackage.SETTLEMENT;
		} else if (domeCount >= 1) {
			cp = CarePackage.DOME;
		} else if (survivalKitCount > 10 && reinforcedSuitCount > 10) {
			cp = (survivalKitCount > reinforcedSuitCount) ? CarePackage.SURVIVAL_KIT : CarePackage.REINFORCED_SUIT;
		} else if (hyperjumpCount >= 1) {
			cp = CarePackage.HYPERJUMP;
		}
		astro.setCarePackage(cp);
		return cp;
	} // 6 energy

	private CarePackage getPackageTypeAttack (UnitController uc, Astronauta astro) {
		StructureInfo structureInfo = uc.getStructureInfo(); // 1 energy
		CarePackage cp = null;

		// Contador de CarePackage
		int reinforcedSuitCount = structureInfo.getCarePackagesOfType(CarePackage.REINFORCED_SUIT);	// 1 energy
		int survivalKitCount = structureInfo.getCarePackagesOfType(CarePackage.SURVIVAL_KIT);		// 1 energy

		if(survivalKitCount > reinforcedSuitCount) {
			cp = CarePackage.SURVIVAL_KIT;
		}
		else{
			cp = CarePackage.REINFORCED_SUIT;
		}

		astro.setCarePackage(cp);
		return cp;
	} // 3 energy



	@SuppressWarnings("InfiniteLoopStatement")
	public void run(UnitController uc) {
		// Code to be executed only at the beginning of the unit's lifespan

		// Inicialitzar l'array de comptadors
		for (int j = 0; j < 9; j++) {
			astronautaTypeCounts[j] = 0;
		}

		Astronauta astro = new Astronauta(getNextAstronautaType(uc), 30, null);

		int patternIndex = 0;

		//noinspection InfiniteLoopStatement
		while (true) {
			// Code to be executed every round
			int dirIndex = (int)(uc.getRandomDouble()*8.0);
			Direction randomDir = directions[dirIndex];

			//Structure HQ
			if (uc.isStructure() && uc.getType() == StructureType.HQ){

				//Comprovar si dintre del tang de visió hi ha algun enemic
				AstronautInfo[] arrayAstronautesEnemics = uc.senseAstronauts(uc.getType().getVisionRange(), uc.getOpponent()); // 203 energy


				// Si s'ha trobat algun enemic
				if(arrayAstronautesEnemics.length > 0){
					AstronautInfo astronautInfo = arrayAstronautesEnemics[0];
					Location locationEnemic = astronautInfo.getLocation();	// 1 energy

					// Calcular direcció
					Direction directionEnemic = uc.getLocation().directionTo(locationEnemic); // 5 energy

					// Crear astronauta cap aquella direcció
					CarePackage cp = getPackageTypeAttack(uc,astro);
					if(uc.canEnlistAstronaut(directionEnemic, 10, cp)) { // 20 energy
						uc.enlistAstronaut(directionEnemic, 10, cp); // 20 energy

					}
					// No ha trobat enemics pel voltant

				} else {
					for (Direction dir : directions) {
						CarePackage cp = getPackageType(uc, astro);
						if (uc.canEnlistAstronaut(dir, astro.getOxigen(), cp)) {    // 20 energy
							uc.enlistAstronaut(dir, astro.getOxigen(), cp);            // 20 energy

							break;
						}
					}
				}

			}

			// Strucutre SETTLEMENT
			else if(uc.isStructure() && uc.getType() == StructureType.SETTLEMENT) {
				// Comprovar si hi ha algun enemic pel rang de visió
				AstronautInfo[] arrayAstronautesEnemics = uc.senseAstronauts(uc.getType().getVisionRange(), uc.getOpponent()); // 203 energy

				// Si s'ha trobat algun enemic
				if(arrayAstronautesEnemics.length > 0) {
					AstronautInfo astronautInfo = arrayAstronautesEnemics[0];
					Location locationEnemic = astronautInfo.getLocation();	// 1 energy

					// Calcular direcció
					Direction directionEnemic = uc.getLocation().directionTo(locationEnemic); // 5 energy

					// Crear astronauta cap aquella direcció
					CarePackage cp = getPackageTypeAttack(uc,astro);
					if(uc.canEnlistAstronaut(directionEnemic, 10, cp)) { // 20 energy
						uc.enlistAstronaut(directionEnemic, 10, cp); // 20 energy
					}
					// No ha trobat enemics pel voltant

				} else {

					for (Direction dir : directions) {
						CarePackage cp = getPackageType(uc, astro);
						if (uc.canEnlistAstronaut(dir, astro.getOxigen(), cp)) {    // 20 energy
							uc.enlistAstronaut(dir, astro.getOxigen(), cp);         // 20 energy
							break;
						}
					}

				}
			}


			//ASTRONAUTA
			else if (!uc.isStructure()){

				Direction directionAtacar = null;

				// Comprovar si hi ha alguna base enemiga pel voltant
				StructureInfo[] arrayEstrucutresEnemigues = uc.senseStructures(25, uc.getOpponent());	// 201 energy

				if (arrayEstrucutresEnemigues.length > 0) {
					// Moure l'astronauta cap a la base enemiga
					StructureInfo estructuraEnemiga = arrayEstrucutresEnemigues[0];
					Location locationEnemic = estructuraEnemiga.getLocation();	// 1 energy

					directionAtacar = uc.getLocation().directionTo(locationEnemic); // 2 energy

					/*
					for (Direction direction : directions) {	// x8
						Location adjLocation = uc.getLocation().add(direction);	// 3 energy
						if (uc.senseStructure(adjLocation) != null) { // 10 energy
							if (uc.senseStructure(adjLocation).getTeam() == uc.getOpponent() && uc.senseStructure(adjLocation).getType() == StructureType.HQ) {
								if (uc.canPerformAction(ActionType.SABOTAGE, direction, 0)) {
									uc.performAction(ActionType.SABOTAGE, direction, 0);

									break;
								}
							}
						}
					}
					 */


				} else {
					// Comprovar si hi ha algun enemic pel voltant
					AstronautInfo[] arrayAstronautesEnemigs = uc.senseAstronauts(25, uc.getOpponent());

					if (arrayAstronautesEnemigs.length > 0) {
						// Moure l'astronauta cap a l'enemic
						AstronautInfo apropEnemic = arrayAstronautesEnemigs[0];
						Location locationEnemic = apropEnemic.getLocation();	// 1 energy

						directionAtacar = uc.getLocation().directionTo(locationEnemic); // 2 energy

					}
				}

				if (directionAtacar != null) {
					if (uc.canPerformAction(ActionType.MOVE, directionAtacar, 0)) {
						uc.performAction(ActionType.MOVE, directionAtacar, 0);
					}

					if (uc.canPerformAction(ActionType.SABOTAGE, directionAtacar, 0)) {
						uc.performAction(ActionType.SABOTAGE, directionAtacar, 0);
					}

				} else {

					// Comprovar si tenen CarePackage
					CarePackage packageEquipat = uc.getAstronautInfo().getCarePackage();

					if (packageEquipat != null && (packageEquipat != CarePackage.REINFORCED_SUIT) && (packageEquipat != CarePackage.SURVIVAL_KIT)) {
						String packageName = packageEquipat.toString();
						astro.accionsPerPackage(uc, packageName, directions);
					} else {

						CarePackageInfo[] arrayCarePackage = uc.senseCarePackages(25);

						if (arrayCarePackage.length > 0 && (uc.getAstronautInfo().getCarePackage() == null)) {

							// Prioritat de CarePackage
							CarePackage[] prioritatCarePackage = {CarePackage.PLANTS, CarePackage.OXYGEN_TANK};

							// Iterar sobre els package amb prioritat per saber si hi han
							for (CarePackage prioritat : prioritatCarePackage) {
								for (CarePackageInfo carePackageInfo : arrayCarePackage) {
									if (carePackageInfo.getCarePackageType() == prioritat) {

										Location locationCarePackage = carePackageInfo.getLocation();

										Direction directionCarePackage = uc.getLocation().directionTo(locationCarePackage);
										if (uc.canPerformAction(ActionType.MOVE, directionCarePackage, 0) && (uc.senseObjectAtLocation(locationCarePackage) != MapObject.WATER)) {
											uc.performAction(ActionType.MOVE, directionCarePackage, 0);
											break;
										}
									}
								}
							}

						} else if (uc.senseTileType(uc.getLocation()) != TileType.HOT_ZONE && (astro.getTipus() % 2 == 0) && (uc.getAstronautInfo().getCarePackage() == null)) {
							Location[] arrayHotZone = uc.senseObjects(MapObject.HOT_ZONE, 25);

							if (arrayHotZone.length > 0) {
								Location locationHotZone = arrayHotZone[0];

								Direction directionHotZone = uc.getLocation().directionTo(locationHotZone);

								if (uc.canPerformAction(ActionType.MOVE, directionHotZone, 0)) {
									uc.performAction(ActionType.MOVE, directionHotZone, 0);
								}
							} else {
								StructureInfo[] arrayStructure = uc.senseStructures(20, uc.getTeam());

								if (arrayStructure.length > 0) {

									for (StructureInfo structureInfo : arrayStructure) {
										if (structureInfo.getType() == StructureType.SETTLEMENT && (structureInfo.getID() != uc.getParent().getID())) {
											Direction direction = uc.getLocation().directionTo(structureInfo.getLocation());

											if (uc.canPerformAction(ActionType.MOVE, direction, 0)) {
												uc.performAction(ActionType.MOVE, direction, 0);
											}
										}
									}
								}
							}
						}


						// Moviments que ha d'executar l'astronauta si no es troba cap objecte d'interes
						int tipus = astro.getTipus();
						Direction dir = null;

						// Moviments dels astronautes, per tipus
						switch (tipus) {
							case 1:
								dir = astro.getMovementPatternNORD()[patternIndex % astro.getMovementPatternNORD().length];
								break;
							case 2:
								dir = astro.getMovementPatternEST()[patternIndex % astro.getMovementPatternEST().length];
								break;
							case 3:
								dir = astro.getMovementPatternSUD()[patternIndex % astro.getMovementPatternSUD().length];
								break;
							case 4:
								dir = astro.getMovementPatternOEST()[patternIndex % astro.getMovementPatternOEST().length];
								break;
							case 5:
								dir = astro.getMovementPatternNORDEST()[patternIndex % astro.getMovementPatternNORDEST().length];
								break;
							case 6:
								dir = astro.getMovementPatternSUDEST()[patternIndex % astro.getMovementPatternSUDEST().length];
								break;
							case 7:
								dir = astro.getMovementPatternSUDOEST()[patternIndex % astro.getMovementPatternSUDOEST().length];
								break;
							case 8:
								dir = astro.getMovementPatternNORDOEST()[patternIndex % astro.getMovementPatternNORDOEST().length];
								break;
							case 9:
								break;
						}

						for (int i = 0; i < 8; i++) {
							if (uc.canPerformAction(ActionType.MOVE, dir, 0)) {
								uc.performAction(ActionType.MOVE, dir, 0);
								patternIndex++;
								break;
							} else {
								switch (tipus) {
									case 1:
										dir = astro.getMovementPatternNORD()[(patternIndex + 1) % astro.getMovementPatternNORD().length];
										break;
									case 2:
										dir = astro.getMovementPatternEST()[(patternIndex + 1) % astro.getMovementPatternEST().length];
										break;
									case 3:
										dir = astro.getMovementPatternSUD()[(patternIndex + 1) % astro.getMovementPatternSUD().length];
										break;
									case 4:
										dir = astro.getMovementPatternOEST()[(patternIndex + 1) % astro.getMovementPatternOEST().length];
										break;
									case 5:
										dir = astro.getMovementPatternNORDEST()[(patternIndex + 1) % astro.getMovementPatternNORDEST().length];
										break;
									case 6:
										dir = astro.getMovementPatternSUDEST()[(patternIndex + 1) % astro.getMovementPatternSUDEST().length];
										break;
									case 7:
										dir = astro.getMovementPatternSUDOEST()[(patternIndex + 1) % astro.getMovementPatternSUDOEST().length];
										break;
									case 8:
										dir = astro.getMovementPatternNORDOEST()[(patternIndex + 1) % astro.getMovementPatternNORDOEST().length];
										break;
									case 9:

										for (int y = 0; y < 8; ++y) {
											//Note that the 'value' of the following command is irrelevant.
											if (uc.canPerformAction(ActionType.MOVE, randomDir, 0)) {
												uc.performAction(ActionType.MOVE, randomDir, 0);
											}
											randomDir = randomDir.rotateRight();
										}

										if (!uc.isTerraformed(uc.getLocation())) {
											if (uc.canPerformAction(ActionType.TERRAFORM, Direction.ZERO, 0)) {
												uc.performAction(ActionType.TERRAFORM, Direction.ZERO, 0);
											}
										}
										break;
								}

								patternIndex++;
								// Comprovar si el moviment no s'ha pogut executar perquè han xocat canviem de patro
								for (Direction direction : directions) {
									Location location = uc.getLocation().add(direction);
									if (uc.isOutOfMap(location) || uc.senseAstronaut(location) != null) {
										int randomNum = (int)(uc.getRandomDouble()*9.0);
										astro.setTipus(randomNum);
									}
								}
							}
						}
					}

					//CarePackage
					for (Direction dir1 : directions) {
						Location adjLocation = uc.getLocation().add(dir1);
						if (!uc.canSenseLocation(adjLocation)) continue;
						CarePackage cp = uc.senseCarePackage(adjLocation);
						if (cp != null && (uc.getAstronautInfo().getCarePackage() == null)) {
							if (uc.canPerformAction(ActionType.RETRIEVE, dir1, 0)) {
								uc.performAction(ActionType.RETRIEVE, dir1, 0);
								break;
							}
						}
					}



					// Si hi ha alguna estructura SETTLEMENT transfereixi nou oxigen
					CarePackage carePackage = uc.getAstronautInfo().getCarePackage();
					for (Direction direction : directions) {
						Location adjlocation = uc.getLocation().add(direction);
						if (uc.senseStructure(adjlocation) != null) {
							StructureInfo structureInfo = uc.senseStructure(adjlocation);
							if ((structureInfo.getType() == StructureType.SETTLEMENT)) {
								if (uc.canPerformAction(ActionType.TRANSFER_OXYGEN, direction, 0)) {
									uc.performAction(ActionType.TRANSFER_OXYGEN, direction, 0);

								}
							}
						}
					}


					//Hyperjump

					dirIndex = (int) (uc.getRandomDouble() * 8.0);
					Direction randomDir1 = directions[dirIndex];

					AstronautInfo[] arrayAstronautesEnemeics = uc.senseAstronauts(25, uc.getOpponent());

					if (arrayAstronautesEnemeics.length > 0) {

						for (AstronautInfo arrayAstronautesEnemeic : arrayAstronautesEnemeics) {

							Location locationAstronautaEnemeic = arrayAstronautesEnemeic.getLocation();

							Direction directionAstronautaEnemeic = uc.getLocation().directionTo(locationAstronautaEnemeic);

							if (uc.canPerformAction(ActionType.JUMP, directionAstronautaEnemeic, 3)) {
								uc.performAction(ActionType.JUMP, directionAstronautaEnemeic, 3);
								break;
							}
						}

					} else {
						boolean salt = false;
						for (Direction direction : directions) {
							Location location = uc.getLocation().add(direction);
							if (uc.senseObjectAtLocation(location) == MapObject.WATER) {
								Direction direction1 = uc.getLocation().directionTo(location);
								if (uc.canPerformAction(ActionType.JUMP, direction1, 3)) {
									uc.performAction(ActionType.JUMP, direction1, 3);
									salt = true;
									break;
								}
							}
						}

						if (!salt) {
							if (uc.canPerformAction(ActionType.JUMP, randomDir1, 3)) {
								uc.performAction(ActionType.JUMP, randomDir1, 3);
							}
						}
					}


					//Quan li quedi poca vida, terraformar
					if (uc.getAstronautInfo().getOxygen() <= 2) {

						if (uc.canPerformAction(ActionType.TERRAFORM, Direction.ZERO, 0)) {

							uc.performAction(ActionType.TERRAFORM, Direction.ZERO, 0);
						} else {
							for (Direction direction : directions) {
								Location adjLocation = uc.getLocation().add(direction);
								if (!uc.isTerraformed(adjLocation)) {
									uc.performAction(ActionType.TERRAFORM, Direction.ZERO, 0);
								}
							}

						}

					}
				}
			}

			uc.yield(); // End of turn
		}
	}
}
