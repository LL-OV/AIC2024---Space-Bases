package Astro;

import aic2024.user.*;
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
	//private int astronautCount = 0;


	// Especificacions dels astronautes
	public Astronauta(int tipus, int oxigen, CarePackage carePackage) {
		this.tipus = tipus;
		this.oxigen = oxigen;
		this.carePackage = carePackage;
	}


	// SETTERS I GETTERS
	// Quantitat d'astronautes
	/*
	public int getAstronautCount() {
		return astronautCount;
	}
	public void setAstronautCount(int astronautCount) {
		this.astronautCount += astronautCount;
	}

	 */

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
/*
	public Direction[] getMovementPatternTR() {
		return movementPatternTR;
	}
 */

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

				for (Direction direction : directions){
					Location adjLocation = uc.getLocation().add(direction);
					if (uc.senseObjectAtLocation(adjLocation) == MapObject.WATER) {
						if (uc.canPerformAction(ActionType.BUILD_HYPERJUMP, Direction.ZERO, 0)) {
							uc.performAction(ActionType.BUILD_HYPERJUMP, Direction.ZERO, 0);
						}

					}
				}

				break;
			case "SETTLEMENT":
				// Per a construir un settlement a d'estar a una certa distrancia de les bases HQ
				// Mirar que hi hagi una diferencia de rang entre les HQ i les Settlement
				Location hqlocation = uc.getParent().getLocation();
				int visionRange = uc.getParent().getType().getVisionRange();
				if(uc.getLocation().distanceSquared(hqlocation) > visionRange){
					if(uc.canPerformAction(ActionType.BUILD_SETTLEMENT, Direction.NORTH, 0)){
						uc.performAction(ActionType.BUILD_SETTLEMENT, Direction.NORTH, 0);
					}
				}
				break;

			default:
				break;
		}

	}


}



public class UnitPlayer {

	final Direction[] directions = Direction.values();

	// Emmagatzemar quantitat d'asctronautes
	int[] astronautaTypeCounts = new int[9];
	private int getNextAstronautaType(UnitController uc) {
		Location baseLocation = uc.getLocation();

		// Calculem en quina zona està la base
		int zoneX = baseLocation.x / (uc.getMapWidth() / 3);
		int zoneY = baseLocation.y / (uc.getMapHeight() / 3);

		List<Integer> availableTypes = getIntegers(uc, baseLocation);

		// Reset el contador de tipus d'astronautes
		if (availableTypes.isEmpty()) {
			for (int i = 0; i < 9; i++) {
				astronautaTypeCounts[i] = 0;
			}
			availableTypes = getIntegers(uc, baseLocation);
		}

		// Random AstronautaType
		int nextType = availableTypes.get((int) (Math.random() * availableTypes.size()));

		// Incrementem el contador per al tipus escollit
		astronautaTypeCounts[nextType - 1]++;

		return nextType;
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

	int astronautaCount =0;

	// CarePackage
	private CarePackage getPackageType(UnitController uc, Astronauta astro) {
		StructureInfo structureInfo = uc.getStructureInfo();
		CarePackage cp = null;

		if(structureInfo.getCarePackagesOfType(CarePackage.SETTLEMENT) >= 1){
			cp = CarePackage.SETTLEMENT;
		} else if(structureInfo.getCarePackagesOfType(CarePackage.DOME) >= 1) {
			cp = CarePackage.DOME;
		} else if(structureInfo.getCarePackagesOfType(CarePackage.SURVIVAL_KIT) > 10 &&(structureInfo.getCarePackagesOfType(CarePackage.REINFORCED_SUIT) > 10)) {
			if(structureInfo.getCarePackagesOfType(CarePackage.SURVIVAL_KIT) > structureInfo.getCarePackagesOfType(CarePackage.REINFORCED_SUIT)) {
				cp = CarePackage.SURVIVAL_KIT;
			}
			else{
				cp = CarePackage.REINFORCED_SUIT;
			}

		} else if (structureInfo.getCarePackagesOfType(CarePackage.HYPERJUMP) >= 1){
			cp = CarePackage.HYPERJUMP;

		}
		astro.setCarePackage(cp);
		return cp;
	}


	@SuppressWarnings("InfiniteLoopStatement")
	public void run(UnitController uc) {
		// Code to be executed only at the beginning of the unit's lifespan

		int patternIndex=0;

		// Inicialitzar l'array de comptadors
		for (int j = 0; j < 9; j++) {
			astronautaTypeCounts[j] = 0;
		}

		Astronauta astro = new Astronauta(getNextAstronautaType(uc), 30, null);


		//noinspection InfiniteLoopStatement
		while (true) {
			// Code to be executed every round
			int dirIndex = (int)(uc.getRandomDouble()*8.0);
			Direction randomDir = directions[dirIndex];

			//Structure HQ
			if (uc.isStructure() && uc.getType() == StructureType.HQ){

				//Comprovar si dintre del tang de visió hi ha algun enemic
				AstronautInfo[] arrayAstronautesEnemics = uc.senseAstronauts(uc.getType().getVisionRange(), uc.getOpponent());
				StructureInfo[] arrayEstructuresEnemigues = uc.senseStructures(uc.getType().getVisionRange(), uc.getOpponent());

				// Si s'ha trobat algun enemic
				if(arrayAstronautesEnemics.length > 0 || arrayEstructuresEnemigues.length > 0){
					AstronautInfo astronautInfo = arrayAstronautesEnemics[0];
					Location locationEnemic = astronautInfo.getLocation();

					// Calcular direcció
					Direction directionEnemic = uc.getLocation().directionTo(locationEnemic);

					// Crear astronauta cap aquella direcció
					if(uc.canEnlistAstronaut(directionEnemic, 10, CarePackage.REINFORCED_SUIT)){
						uc.enlistAstronaut(directionEnemic,10, CarePackage.REINFORCED_SUIT);
					}else if(uc.canEnlistAstronaut(directionEnemic, 10, CarePackage.SURVIVAL_KIT)){
						uc.enlistAstronaut(directionEnemic, 10, CarePackage.SURVIVAL_KIT);
					} else if (uc.canEnlistAstronaut(directionEnemic, 10, getPackageType(uc, astro))) {
						uc.enlistAstronaut(directionEnemic, 10, getPackageType(uc,astro));
					}
					// No ha trobat enemics pel voltant

				} else {

					CarePackageInfo[] arrayCarePackage = uc.senseCarePackages(uc.getType().getVisionRange());

					if(arrayCarePackage.length>0) {

						boolean enlist = false;
						do {
							Location locationCarePackage = arrayCarePackage[0].getLocation();
							Direction directionCarePackage = uc.getLocation().directionTo(locationCarePackage);
							if (uc.canEnlistAstronaut(directionCarePackage, astro.getOxigen(), getPackageType(uc, astro))) {
								uc.enlistAstronaut(directionCarePackage, astro.getOxigen(), getPackageType(uc, astro));
								enlist = true;
							}
						} while (!enlist);
					}
					else {
						for (Direction dir : directions) {
							if (uc.canEnlistAstronaut(dir, astro.getOxigen(), getPackageType(uc, astro))) {
								uc.enlistAstronaut(dir, astro.getOxigen(), getPackageType(uc, astro));
								break;
							}
						}
					}


				}


			}

			// Strucutre SETTLEMENT
			else if(uc.isStructure() && uc.getType() == StructureType.SETTLEMENT) {
				// Comprovar si hi ha algun enemic pel rang de visió
				AstronautInfo[] arrayAstronautesEnemics = uc.senseAstronauts(uc.getType().getVisionRange(), uc.getOpponent());

				// Si s'ha trobat algun enemic
				if(arrayAstronautesEnemics.length > 0) {
					AstronautInfo astronautInfo = arrayAstronautesEnemics[0];
					Location locationEnemic = astronautInfo.getLocation();

					// Calcular direcció
					Direction directionEnemic = uc.getLocation().directionTo(locationEnemic);

					// Crear astronauta cap aquella direcció
					if(uc.canEnlistAstronaut(directionEnemic, 10, CarePackage.REINFORCED_SUIT)){
						uc.enlistAstronaut(directionEnemic,10, CarePackage.REINFORCED_SUIT);
					}else if(uc.canEnlistAstronaut(directionEnemic, 10, CarePackage.SURVIVAL_KIT)){
						uc.enlistAstronaut(directionEnemic, 10, CarePackage.SURVIVAL_KIT);
					} else if (uc.canEnlistAstronaut(directionEnemic, 10, getPackageType(uc, astro))) {
						uc.enlistAstronaut(directionEnemic, 10, getPackageType(uc,astro));
					}

					for (Direction dir : directions) {
						if (uc.canEnlistAstronaut(dir, astro.getOxigen(), getPackageType(uc, astro))) {
							uc.enlistAstronaut(dir, astro.getOxigen(), getPackageType(uc, astro));
							break;
						}
					}

				} else {

					for (Direction dir : directions) {
						if (uc.canEnlistAstronaut(dir, astro.getOxigen(), getPackageType(uc, astro))) {
							uc.enlistAstronaut(dir, astro.getOxigen(), getPackageType(uc, astro));
							break;
						}
					}
				}
			}
			

			//ASTRONAUTA
			else if (!uc.isStructure()){

				// Comprovar si hi ha alguna base enemiga pel voltant
				StructureInfo[] arrayEstrucutresEnemigues = uc.senseStructures(25, uc.getOpponent());

				if (arrayEstrucutresEnemigues.length > 0) {
					// Moure l'astronauta cap a la base enemiga
					StructureInfo estructuraEnemiga = arrayEstrucutresEnemigues[0];
					Location locationEnemic = estructuraEnemiga.getLocation();

					Direction directionEstructuraAtacar = uc.getLocation().directionTo(locationEnemic);

					if (uc.canPerformAction(ActionType.MOVE, directionEstructuraAtacar, 0)) {
						uc.performAction(ActionType.MOVE, directionEstructuraAtacar, 0);
					}

					// Sabotatge a la base enemiga
					for (Direction direction : directions) {
						Location adjLocation = uc.getLocation().add(direction);
						if (uc.senseStructure(adjLocation) != null) {
							if (uc.senseStructure(adjLocation).getTeam() == uc.getOpponent() && uc.senseStructure(adjLocation).getType() == StructureType.HQ) {
								if (uc.canPerformAction(ActionType.SABOTAGE, direction, 0)) {
									uc.performAction(ActionType.SABOTAGE, direction, 0);
									astronautaCount--;
									break;
								}
							}
						}
					}

				} else {
					// Comprovar si hi ha algun enemic pel voltant
					AstronautInfo[] arrayAstronautesEnemigs = uc.senseAstronauts(25, uc.getOpponent());

					if (arrayAstronautesEnemigs.length > 0) {
						// Moure l'astronauta cap a l'enemic
						AstronautInfo apropEnemic = arrayAstronautesEnemigs[0];
						Location locationEnemic = apropEnemic.getLocation();

						Direction directionAstornautaAtacar = uc.getLocation().directionTo(locationEnemic);

						if (uc.canPerformAction(ActionType.MOVE, directionAstornautaAtacar, 0)) {
							uc.performAction(ActionType.MOVE, directionAstornautaAtacar, 0);
						}

						// Sabotatge a l'enemic
						for (Direction direction : directions) {
							Location adjLocation = uc.getLocation().add(direction);
							if (uc.senseAstronaut(adjLocation) != null) {
								if (uc.senseAstronaut(adjLocation).getTeam() == uc.getOpponent()) {
									if (uc.canPerformAction(ActionType.SABOTAGE, direction, 0)) {
										uc.performAction(ActionType.SABOTAGE, direction, 0);
										astronautaCount--;
										break;
									}
								}
							}
						}
					} else {

						CarePackageInfo[] arrayCarePackage= uc.senseCarePackages(25);

						if(arrayCarePackage.length> 0 &&(uc.getAstronautInfo().getCarePackage() == null)) {

							// Prioritat de CarePackage
							CarePackage[] prioritatCarePackage = {CarePackage.PLANTS, CarePackage.OXYGEN_TANK};

							// Iterar sobre els package amb prioritat per saber si hi han
							for (CarePackage prioritat : prioritatCarePackage) {
								for (CarePackageInfo carePackageInfo : arrayCarePackage) {
									if (carePackageInfo.getCarePackageType() == prioritat) {

										Location locationCarePackage = carePackageInfo.getLocation();

										Direction directionCarePackage = uc.getLocation().directionTo(locationCarePackage);
										if (uc.canPerformAction(ActionType.MOVE, directionCarePackage, 0) &&(uc.senseObjectAtLocation(locationCarePackage) != MapObject.WATER )) {
											uc.performAction(ActionType.MOVE, directionCarePackage, 0);
											break;
										}
									}
								}
							}

						} else if (uc.senseTileType(uc.getLocation()) != TileType.HOT_ZONE && (astro.getTipus() % 2== 0) && (uc.getAstronautInfo().getCarePackage() == null)) {
							Location[] arrayHotZone = uc.senseObjects(MapObject.HOT_ZONE, 25);

							if (arrayHotZone.length > 0) {
								Location locationHotZone = arrayHotZone[0];

								Direction directionHotZone = uc.getLocation().directionTo(locationHotZone);

								if (uc.canPerformAction(ActionType.MOVE, directionHotZone, 0)) {
									uc.performAction(ActionType.MOVE, directionHotZone, 0);
								}
							}
							else{
								StructureInfo[] arrayStructure = uc.senseStructures(20, uc.getTeam());

								if (arrayStructure.length > 0) {

									for(StructureInfo structureInfo : arrayStructure){
										if(structureInfo.getType() == StructureType.SETTLEMENT &&(structureInfo.getID() != uc.getParent().getID())){
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
													astronautaCount--;
												}
											}
											break;
									}

								patternIndex++;
								// Comprovar si el moviment no s'ha pogut executar perquè han xocat canviem de patro

								for (Direction direction : directions) {
									Location location = uc.getLocation().add(direction);
									if (uc.isOutOfMap(location) || uc.senseAstronaut(location) != null) {
										int randomNumber = (int) (uc.getRandomDouble() * 8.0);
										astro.setTipus(randomNumber);
									}
								}
							}
						}
					}
				}


				// Comprovar si tenen CarePackage
				CarePackage packageEquipat = uc.getAstronautInfo().getCarePackage();
				if (packageEquipat != null) {
					String packageName = packageEquipat.toString();
					astro.accionsPerPackage(uc, packageName, directions);
				}


				//CarePackage
				for (Direction dir1 : directions) {
					Location adjLocation = uc.getLocation().add(dir1);
					if (!uc.canSenseLocation(adjLocation)) continue;
					CarePackage cp = uc.senseCarePackage(adjLocation);
					StructureInfo parentS = uc.getParent();
					if (cp != null && (uc.getAstronautInfo().getCarePackage() == null) &&
							(parentS.getCarePackagesOfType(CarePackage.REINFORCED_SUIT) < 20)||
							(parentS.getCarePackagesOfType(CarePackage.SURVIVAL_KIT) < 20) ||
							(parentS.getCarePackagesOfType(CarePackage.HYPERJUMP) < 20)) {
						if (uc.canPerformAction(ActionType.RETRIEVE, dir1, 0)) {
							uc.performAction(ActionType.RETRIEVE, dir1, 0);
							astronautaCount--;
							break;
						}
					}
				}


				// Si hi ha alguna estructura SETTLEMENT transfereixi nou oxigen
				CarePackage carePackage = uc.getAstronautInfo().getCarePackage();
				for(Direction direction : directions) {
					Location adjlocation = uc.getLocation().add(direction);
					if(uc.senseStructure(adjlocation) != null){
						StructureInfo structureInfo = uc.senseStructure(adjlocation);
						if((structureInfo.getType() == StructureType.SETTLEMENT)) {
							if (uc.canPerformAction(ActionType.TRANSFER_OXYGEN, direction, 0)){
								uc.performAction(ActionType.TRANSFER_OXYGEN,direction, 0);
								astronautaCount--;
							}
						}
					}
				}


				//Hyperjump

				dirIndex = (int)(uc.getRandomDouble()*8.0);
				Direction randomDir1 = directions[dirIndex];

				AstronautInfo[] arrayAstronautesEnemeics = uc.senseAstronauts(25, uc.getOpponent());

				if(arrayAstronautesEnemeics.length > 0) {

                    for (AstronautInfo arrayAstronautesEnemeic : arrayAstronautesEnemeics) {

                        Location locationAstronautaEnemeic = arrayAstronautesEnemeic.getLocation();

                        Direction directionAstronautaEnemeic = uc.getLocation().directionTo(locationAstronautaEnemeic);

                        if (uc.canPerformAction(ActionType.JUMP, directionAstronautaEnemeic, 3)) {
                            uc.performAction(ActionType.JUMP, directionAstronautaEnemeic, 3);
                            break;
                        }
                    }

				} else{
					boolean salt = false;
					for(Direction direction : directions) {
						Location location = uc.getLocation().add(direction);
						if(uc.senseObjectAtLocation(location) == MapObject.WATER) {
							Direction direction1 = uc.getLocation().directionTo(location);
							if (uc.canPerformAction(ActionType.JUMP, direction1, 3)) {
								uc.performAction(ActionType.JUMP, direction1, 3);
								salt = true;
								break;
							}
						}
					}

					if(!salt) {
						if (uc.canPerformAction(ActionType.JUMP, randomDir1, 3)) {
							uc.performAction(ActionType.JUMP, randomDir1, 3);
						}
					}
				}



				//Quan li quedi poca vida, terraformar
				if (uc.getAstronautInfo().getOxygen() <= 2) {
					astronautaCount--;
					if (uc.canPerformAction(ActionType.TERRAFORM, Direction.ZERO, 0)){

						uc.performAction(ActionType.TERRAFORM, Direction.ZERO, 0);
					}
					else {
						for (Direction direction : directions) {
							Location adjLocation = uc.getLocation().add(direction);
							if (!uc.isTerraformed(adjLocation)) {
								uc.performAction(ActionType.TERRAFORM, Direction.ZERO, 0);
							}
						}

					}

				}

			}

			uc.yield(); // End of turn
		}
	}
}
