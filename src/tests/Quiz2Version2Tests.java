package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.Player;
import exceptions.BuildingInCoolDownException;
import exceptions.DowngradeLimitExeption;
import exceptions.MaxLevelException;
import exceptions.MinLevelException;
@SuppressWarnings({ "rawtypes", "unchecked","unused" })
public class Quiz2Version2Tests {
	
	String playerPath = "engine.Player";
	String militaryBuildingPath = "buildings.MilitaryBuilding";
	String archeryRangePath = "buildings.ArcheryRange";
	String barracksPath = "buildings.Barracks";
	String stablePath = "buildings.Stable";
	
	//Buildings tests
	@Test(timeout = 100)
	public void testInstanceVariableMilitaryBuildingDowngradeGoldBack() throws Exception {
		testInstanceVariableIsPresent(Class.forName(militaryBuildingPath), "downgradeGoldBack", true);
		testInstanceVariableIsPrivate(Class.forName(militaryBuildingPath), "downgradeGoldBack");
		testInstanceVariableOfType(Class.forName(militaryBuildingPath), "downgradeGoldBack", int.class);
	}
	
	
	@Test(timeout = 100)
	public void testMilitaryBuildingDowngradeGoldBackSetter() throws Exception {
		testSetterMethodExistsInClass(Class.forName(militaryBuildingPath),"setDowngradeGoldBack", int.class, true);
		String[] subClasses = {archeryRangePath, barracksPath, stablePath};
		testSetterAbsentInSubclasses("downgradeGoldBack", subClasses);
		
		Constructor<?> constructor = Class.forName(stablePath).getConstructor();
		Object b = constructor.newInstance();
		int randomDowngradeGoldBack = (int) (Math.random() * 1500) + 500;
		testSetterLogic(b, "downgradeGoldBack", randomDowngradeGoldBack, randomDowngradeGoldBack, int.class);
		
	}
	
	@Test(timeout = 100)
	public void testMilitaryBuildingDowngradeGoldBackGetter() throws Exception {
		
		testGetterMethodExistsInClass(Class.forName(militaryBuildingPath),"getDowngradeGoldBack", int.class, true);
		String[] subClasses = {archeryRangePath, barracksPath, stablePath};
		testGetterAbsentInSubclasses("downgradeGoldBack", subClasses, int.class);
		Constructor<?> constructor = Class.forName(stablePath).getConstructor();
		Object b = constructor.newInstance();
		int randomDowngradeGoldBack = (int) (Math.random() * 1500) + 500;
		testGetterLogic(b, "downgradeGoldBack", randomDowngradeGoldBack);
	}
	
	
	

	@Test(timeout = 3000)
	public void testArcheryRangeDowngrade() throws Exception {
		testExistsInClass(ArcheryRange.class, "downgrade", true, void.class);
	}
	@Test(timeout = 3000)
	public void testBarracksDowngrade() throws Exception {
		testExistsInClass(Barracks.class, "downgrade", true, void.class);
	}
	




	@Test(timeout = 1000)
	public void testArcheryRangeDowngradeLogic1() throws Exception {

		ArcheryRange ar= new ArcheryRange();
		ar.setCoolDown(false);
		ar.setLevel(2);
		try {

			Method downgrade = ArcheryRange.class.getDeclaredMethod("downgrade");

			downgrade.invoke(ar);

			assertTrue("Value of level is wrong, expected " + 1 + " but was " + ar.getLevel() + ".", 1 == ar.getLevel());
			
			assertTrue("Value of upgrade cost is wrong, expected " + 800 + " but was " + ar.getUpgradeCost() + ".",
					800 == ar.getUpgradeCost());
			assertTrue("Value of downgrade gold back is wrong, expected " + 500 + " but was " + ar.getDowngradeGoldBack() + ".",
					500 == ar.getDowngradeGoldBack());
			assertTrue("Value of recruitment cost is wrong, expected " + 400 + " but was " + ar.getRecruitmentCost() + ".",
					400 == ar.getRecruitmentCost());
			assertTrue("Value of coolDown is wrong,expected True but was false",ar.isCoolDown());
			
			
		} catch (NoSuchMethodException e) {
			fail("ArcheryRange class should have downgrade method");
		}

	}
	
	@Test(timeout = 1000)
	public void testArcheryRangeDowngradeLogic2() throws Exception {

		ArcheryRange ar= new ArcheryRange();
		ar.setCoolDown(false);
		ar.setLevel(3);
		try {

			Method downgrade = ArcheryRange.class.getDeclaredMethod("downgrade");

			downgrade.invoke(ar);
			
			
			assertTrue("Value of level is wrong, expected " + 2 + " but was " + ar.getLevel() + ".", 2 == ar.getLevel());
			assertTrue("Value of upgrade cost is wrong, expected " + 700 + " but was " + ar.getUpgradeCost() + ".",
					700 == ar.getUpgradeCost());
			assertTrue("Value of downgrade gold back is wrong, expected " + 300 + " but was " + ar.getDowngradeGoldBack() + ".",
					300 == ar.getDowngradeGoldBack());
			assertTrue("Value of recruitment cost is wrong, expected " + 450 + " but was " + ar.getRecruitmentCost() + ".",
					450 == ar.getRecruitmentCost());
			assertTrue("Value of coolDown is wrong,expected True but was false",ar.isCoolDown());

		} catch (NoSuchMethodException e) {
			fail("ArcheryRange class should have downgrade method");
		}

	}
	
	


	@Test(timeout = 3000, expected = BuildingInCoolDownException.class)
	public void testDowngradeBuildingInCoolDownException() throws Throwable {

		ArcheryRange ar = new ArcheryRange();
		ar.setLevel(2);

		try {
			ar.downgrade();

		} catch (Exception ite) {
			if (ite.getCause() instanceof BuildingInCoolDownException) {
				throw ite.getCause();
			} else {
				throw ite;
			}
		}

	}



	@Test(timeout = 3000, expected = MinLevelException.class)
	public void testDowngradeBuildingMinLevelException() throws Throwable {

		ArcheryRange ar = new ArcheryRange();
		ar.setCoolDown(false);


		try {
			ar.downgrade();

		} catch (Exception ite) {
			if (ite.getCause() instanceof MinLevelException) {
				throw ite.getCause();
			} else {
				throw ite;
			}
		}

	}


	//Player tests
	
	@Test(timeout = 100)
	public void testInstanceVariablePlayerDowngradeLimit() throws Exception {
		testInstanceVariableIsPresent(Class.forName(playerPath), "downgradeLimit", true);
		testInstanceVariableIsPrivate(Class.forName(playerPath), "downgradeLimit");
		testInstanceVariableOfType(Class.forName(playerPath), "downgradeLimit", int.class);
	}
	@Test(timeout = 3000, expected = DowngradeLimitExeption.class)
	public void testDowngradeBuildingDowngradeLimitExeption() throws Throwable {

		ArcheryRange ar = new ArcheryRange();
		ar.setCoolDown(false);
		ar.setLevel(3);
		Player p =new Player("Cairo");
		
		

		try {
			p.downgradeBuilding(ar);
			ar.setCoolDown(false);
			p.downgradeBuilding(ar);
			ar.setCoolDown(false);
			ar.setLevel(3);
			p.downgradeBuilding(ar);

		} catch (Exception ite) {
			if (ite.getCause() instanceof DowngradeLimitExeption) {
				throw ite.getCause();
			} else {
				throw ite;
			}
		}

	}
	@Test(timeout = 100)
	public void testPlayerDownGradeLogic1() throws Exception {
		ArcheryRange ar = new ArcheryRange();
		ar.setCoolDown(false);
		ar.setLevel(3);
		Player p =new Player("Cairo");
		p.downgradeBuilding(ar);
		assertEquals("Building level should be 2", 2,ar.getLevel());
		ar.setCoolDown(false);
		p.downgradeBuilding(ar);
		
		assertEquals("Building level should be 1", 1,ar.getLevel());
	}
	@Test(timeout = 100)
	public void testPlayerDownGradeLogic2() throws Exception {
		ArcheryRange ar = new ArcheryRange();
		ar.setCoolDown(false);
		ar.setLevel(3);
		
		Player p =new Player("Cairo");
		p.setTreasury(1000);
		p.downgradeBuilding(ar);
		assertEquals("Player treasury should be", 1300,(int)p.getTreasury());
		
	}
	
	
	
	
	
	
	///////////////////////// Helper methods//////////////////////////////////
	private void testExistsInClass(Class aClass, String methodName, boolean implementedMethod, Class returnType,
			Class... inputTypes) {

		Method[] methods = aClass.getDeclaredMethods();

		if (implementedMethod) {
			assertTrue("The " + methodName + " method in class " + aClass.getSimpleName() + " should be implemented.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse(
					"The " + methodName + " method in class " + aClass.getSimpleName()
					+ " should not be implemented, only inherited from super class.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
		} catch (Exception e) {
			found = false;
		}

		String inputsList = "";
		for (Class inputType : inputTypes) {
			inputsList += inputType.getSimpleName() + ", ";
		}
		if (inputsList.equals(""))
			assertTrue(
					aClass.getSimpleName() + " class should have " + methodName + " method that takes no parameters.",
					found);
		else {
			if (inputsList.charAt(inputsList.length() - 1) == ' ')
				inputsList = inputsList.substring(0, inputsList.length() - 2);
			assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes " + inputsList
					+ " parameter(s).", found);
		}

		assertTrue("incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(returnType));

	}
	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar)
			throws SecurityException {

		boolean thrown = false;
		try {
			aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse(
					"There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".",
					thrown);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should not be declared in class "
					+ aClass.getSimpleName() + ".", thrown);
		}
	}

	private void testInstanceVariableOfType(Class aClass, String varName, Class expectedType) {
		Field f = null;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			return;
		}
		Class varType = f.getType();
		assertEquals(
				"the attribute " + varType.getSimpleName() + " should be of the type " + expectedType.getSimpleName(),
				expectedType, varType);
	}

	private void testInstanceVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		assertEquals("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
				+ " should not be accessed outside that class.", false, f.isAccessible());
	}

	private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType,
			boolean readvariable) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = "";
		if (returnedType == boolean.class)
			varName = methodName.substring(2,3).toLowerCase() + methodName.substring(3);
		else
			varName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
		if (readvariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a READ variable.", found);
			assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.",
					m.getReturnType().isAssignableFrom(returnedType));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a READ variable.", found);
		}

	}

	private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType,
			boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
		if (writeVariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a WRITE variable.", containsMethodName(methods, methodName));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a WRITE variable.", containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one "
				+ inputType.getSimpleName() + " parameter.", found);

		assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(Void.TYPE));

	}

	private void testSetterAbsentInSubclasses(String varName, String[] subclasses)
			throws SecurityException, ClassNotFoundException {
		String methodName = "set" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);

		}
		assertFalse("The " + methodName + " method should not be implemented in a subclasses.", methodIsInSubclasses);
	}

	private void testGetterAbsentInSubclasses(String varName, String[] subclasses, Class type)
			throws SecurityException, ClassNotFoundException {
		String methodName = "get" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		if (type == boolean.class) {
			methodName = "is" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
		}
		boolean methodIsInSubclasses = false;
		for (String subclass : subclasses) {
			Method[] methods = Class.forName(subclass).getDeclaredMethods();
			methodIsInSubclasses = methodIsInSubclasses || containsMethodName(methods, methodName);

		}
		assertFalse("The " + methodName + " method should not be implemented in subclasses.", methodIsInSubclasses);
	}
	private void testGetterLogic(Object createdObject, String name, Object value) throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);
		f.set(createdObject, value);

		Character c = name.charAt(0);

		String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());

		if (value.getClass().equals(Boolean.class))
			methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());

		Method m = createdObject.getClass().getMethod(methodName);
		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should return the correct value of variable \"" + name + "\".",
				value, m.invoke(createdObject));

	}

	private void testSetterLogic(Object createdObject, String name, Object setValue, Object expectedValue, Class type)
			throws Exception {

		Field f = null;
		Class curr = createdObject.getClass();

		while (f == null) {

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
						+ name + "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}

		}

		f.setAccessible(true);

		Character c = name.charAt(0);
		String methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
		Method m = createdObject.getClass().getMethod(methodName, type);
		m.invoke(createdObject, setValue);

		assertEquals(
				"The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName()
						+ " should set the correct value of variable \"" + name + "\".",
				expectedValue, f.get(createdObject));

	}



}
