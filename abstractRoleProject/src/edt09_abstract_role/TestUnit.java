
/*
 * TestJunit.java 10 May 2023
 *
 *
 * \u00A9 Copyright 2023 Joan S�culi
 * Email: jseculi@escoladeltreball.org
 *
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */

package edt09_abstract_role;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUnit {

	Role outlander;
	Role soldier;
	Role constructor;
	Role ninja;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	void outlanderTest() {
		outlander = new Outlander(0, 0);
		outlander.move();
		assertEquals(8.5, outlander.getPosX(), 0.1);
		assertEquals(99.5, outlander.getEnergy(), 0.1);
		outlander.jump();
		assertEquals(6.2, outlander.getPosY(), 0.1);
		assertEquals(98.5, outlander.getEnergy(), 0.1);

		outlander.move();
		assertEquals(8.5 + 8.5, outlander.getPosX(), 0.1);
		assertEquals(98, outlander.getEnergy(), 0.1);

		outlander.jump();
		assertEquals(6.2 + 6.2, outlander.getPosY(), 0.1);
		assertEquals(97, outlander.getEnergy(), 0.1);

		outlander.extraMove();
		assertEquals(8.5 + 8.5 + 20.5, outlander.getPosX(), 0.1);
		assertEquals(95, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(6.2 + 6.2 + 25.2, outlander.getPosY(), 0.1);
		assertEquals(91, outlander.getEnergy(), 0.1);

		outlander.resetPosition();
		assertEquals(0, outlander.getPosX());
		assertEquals(0, outlander.getPosY());
		assertEquals(91, outlander.getEnergy(), 0.1);

		outlander.move();
		outlander.jump();
		outlander.extraMove();
		outlander.extraJump();

		outlander.resetRole();
		assertEquals(0, outlander.getPosX());
		assertEquals(0, outlander.getPosY());
		assertEquals(100, outlander.getEnergy(), 0.1);
		assertEquals(3, outlander.getLifes());

		// Jump until we don't have energy
		outlander.setEnergy(50);
		outlander.extraJump();
		assertEquals(46, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(42, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(38, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(34, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(30, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(26, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(22, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(18, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(14, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(10, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(6, outlander.getEnergy(), 0.1);

		outlander.extraJump();
		assertEquals(2, outlander.getEnergy(), 0.1);

		//Impossible to jump because energy is 2 and it needs 4
		outlander.extraJump();
		assertEquals(2, outlander.getEnergy(), 0.1);

	}

	@Test
	@Order(2)
	void outlanderTest2() {
		outlander = new Outlander(0, 0);
		outlander.setAvailable(false);
		outlander.move();

		assertEquals(0, outlander.getPosX(), 0.1);
		outlander.jump();
		assertEquals(0, outlander.getPosY(), 0.1);

		outlander.extraMove();
		assertEquals(0, outlander.getPosX(), 0.1);

		outlander.extraJump();
		assertEquals(0, outlander.getPosY(), 0.1);

		assertEquals(100, outlander.energy, 0.1);

	}

	@Test
	@Order(3)
	void outlanderTest3() {
		outlander = new Outlander(0, 0);
		outlander.setEnergy(0.2f);
		outlander.move();
		assertEquals(0.2, outlander.getEnergy(), 0.1);

		outlander.jump();
		assertEquals(0.2, outlander.getEnergy(), 0.1);

	}

	@Test
	@Order(4)
	void soldierTest() {
		soldier = new Soldier(0, 0);
		soldier.move();
		assertEquals(10.5, soldier.getPosX(), 0.1);
		assertEquals(99.5, soldier.getEnergy(), 0.1);

		soldier.jump();
		assertEquals(8, soldier.getPosY(), 0.1);
		assertEquals(98.5, soldier.getEnergy(), 0.1);

		soldier.move();
		assertEquals(10.5 + 10.5, soldier.getPosX(), 0.1);
		assertEquals(98, soldier.getEnergy(), 0.1);

		soldier.jump();
		assertEquals(8 + 8, soldier.getPosY(), 0.1);
		assertEquals(97, soldier.getEnergy(), 0.1);

		soldier.extraMove();
		assertEquals(10.5 + 10.5 + 20.5, soldier.getPosX(), 0.1);
		assertEquals(95, soldier.getEnergy(), 0.1);

		soldier.extraJump();
		assertEquals(8 + 8 + 25.2, soldier.getPosY(), 0.1);
		assertEquals(91, soldier.getEnergy(), 0.1);

		soldier.resetPosition();
		assertEquals(0, soldier.getPosX());
		assertEquals(0, soldier.getPosY());
		assertEquals(91, soldier.getEnergy(), 0.1);

		soldier.move();
		soldier.jump();
		soldier.extraMove();
		soldier.extraJump();

		soldier.resetRole();
		assertEquals(0, soldier.getPosX());
		assertEquals(0, soldier.getPosY());
		assertEquals(100, soldier.getEnergy(), 0.1);
		assertEquals(3, soldier.getLifes(), 0.1);

	}

	@Test
	@Order(5)
	void soldierTest2() {
		soldier = new Soldier(0, 0);
		soldier.setAvailable(false);
		soldier.move();
		assertEquals(0, soldier.getPosX(), 0.1);

		soldier.jump();
		assertEquals(0, soldier.getPosY(), 0.1);

		soldier.extraMove();
		assertEquals(0, soldier.getPosX(), 0.1);

		soldier.extraJump();
		assertEquals(0, soldier.getPosY(), 0.1);

		assertEquals(100, soldier.energy, 0.1);

	}

	@Test
	@Order(6)
	void soldierTest3() {
		soldier = new Outlander(0, 0);
		soldier.setEnergy(0.2f);
		soldier.move();
		assertEquals(0.2, soldier.getEnergy(), 0.1);

		soldier.jump();
		assertEquals(0.2, soldier.getEnergy(), 0.1);

	}

	@Test
	@Order(7)
	void constructorTest() {
		constructor = new Constructor(0, 0);
		constructor.move();
		assertEquals(5, constructor.getPosX(), 0.1);
		assertEquals(99.5, constructor.energy, 0.1);

		constructor.jump();
		assertEquals(5, constructor.getPosY(), 0.1);
		assertEquals(98.5, constructor.energy, 0.1);

		constructor.move();
		assertEquals(5 + 5, constructor.getPosX(), 0.1);
		assertEquals(98, constructor.energy, 0.1);

		constructor.jump();
		assertEquals(5 + 5, constructor.getPosY(), 0.1);
		assertEquals(97, constructor.energy, 0.1);

		constructor.extraMove();
		assertEquals(5 + 5 + 20.5, constructor.getPosX(), 0.1);
		assertEquals(95, constructor.energy, 0.1);

		constructor.extraJump();
		assertEquals(5 + 5 + 25.2, constructor.getPosY(), 0.1);
		assertEquals(91, constructor.energy, 0.1);

		constructor.resetPosition();
		assertEquals(0, constructor.getPosX());
		assertEquals(0, constructor.getPosY());
		assertEquals(91, constructor.energy, 0.1);

		constructor.move();
		constructor.jump();
		constructor.extraMove();
		constructor.extraJump();

		constructor.resetRole();
		assertEquals(0, constructor.getPosX());
		assertEquals(0, constructor.getPosY());
		assertEquals(100, constructor.getEnergy(), 0.1);
		assertEquals(3, constructor.getLifes(), 0.1);

	}

	@Test
	@Order(8)
	void constructorTest2() {
		constructor = new Constructor(0, 0);
		constructor.setAvailable(false);
		constructor.move();
		assertEquals(0, constructor.getPosX(), 0.1);
		constructor.jump();
		assertEquals(0, constructor.getPosY(), 0.1);

		constructor.extraMove();
		assertEquals(0, constructor.getPosX(), 0.1);

		constructor.extraJump();
		assertEquals(0, constructor.getPosY(), 0.1);

		assertEquals(100, constructor.energy, 0.1);

	}

	@Test
	@Order(9)
	void constructorTest3() {
		constructor = new Outlander(0, 0);
		constructor.setEnergy(0.2f);
		constructor.move();
		assertEquals(0.2, constructor.getEnergy(), 0.1);

		constructor.jump();
		assertEquals(0.2, constructor.getEnergy(), 0.1);

	}

	@Test
	@Order(10)
	void ninjaTest() {

		ninja = new Ninja(0, 0);
		ninja.move();
		assertEquals(8, ninja.getPosX(), 0.1);
		assertEquals(99.5, ninja.energy, 0.1);

		ninja.jump();
		assertEquals(7.5, ninja.getPosY(), 0.1);
		assertEquals(98.5, ninja.energy, 0.1);

		ninja.move();
		assertEquals(8 + 8, ninja.getPosX(), 0.1);
		assertEquals(98, ninja.energy, 0.1);

		ninja.jump();
		assertEquals(7.5 + 7.5, ninja.getPosY(), 0.1);
		assertEquals(97, ninja.energy, 0.1);

		ninja.extraMove();
		assertEquals(8 + 8 + 20.5, ninja.getPosX(), 0.1);
		assertEquals(95, ninja.energy, 0.1);

		ninja.extraJump();
		assertEquals(7.5 + 7.5 + 25.2, ninja.getPosY(), 0.1);
		assertEquals(91, ninja.energy, 0.1);

		ninja.resetPosition();
		assertEquals(0, ninja.getPosX());
		assertEquals(0, ninja.getPosY());
		assertEquals(91, ninja.energy, 0.1);

		ninja.move();
		ninja.jump();
		ninja.extraMove();
		ninja.extraJump();

		ninja.resetRole();
		assertEquals(0, ninja.getPosX());
		assertEquals(0, ninja.getPosY());
		assertEquals(100, ninja.getEnergy(), 0.1);
		assertEquals(3, ninja.getLifes(), 0.1);

	}

	@Test
	@Order(11)
	void ninjaTest2() {
		ninja = new Ninja(0, 0);
		ninja.setAvailable(false);
		ninja.move();
		assertEquals(0, ninja.getPosX(), 0.1);
		ninja.jump();
		assertEquals(0, ninja.getPosY(), 0.1);

		ninja.extraMove();
		assertEquals(0, ninja.getPosX(), 0.1);

		ninja.extraJump();
		assertEquals(0, ninja.getPosY(), 0.1);
		assertEquals(100, ninja.energy, 0.1);

	}

	@Test
	@Order(12)
	void ninjaTest3() {
		ninja = new Outlander(0, 0);
		ninja.setEnergy(0.2f);
		ninja.move();
		assertEquals(0.2, ninja.getEnergy(), 0.1);

		ninja.jump();
		assertEquals(0.2, ninja.getEnergy(), 0.1);

	}

	@Test
	@Order(13)
	void lifesOutlanderTest() {

		outlander = new Outlander(0, 0);

		assertEquals(3, outlander.getLifes());

		assertTrue(outlander.decreaseLife());
		assertEquals(2, outlander.getLifes());

		assertTrue(outlander.decreaseLife());
		assertEquals(1, outlander.getLifes());

		assertTrue(outlander.decreaseLife());
		assertEquals(0, outlander.getLifes());

		assertFalse(outlander.decreaseLife());
		assertEquals(0, outlander.getLifes());

		assertTrue(outlander.increaseLife());
		assertEquals(1, outlander.getLifes());

		assertTrue(outlander.increaseLife());
		assertEquals(2, outlander.getLifes());

		assertTrue(outlander.increaseLife());
		assertEquals(3, outlander.getLifes());

		assertFalse(outlander.increaseLife());
		assertEquals(3, outlander.getLifes());

		outlander.setAvailable(false);
		assertFalse(outlander.decreaseLife());

		outlander.setAvailable(true);
		outlander.setLifes((byte) 2);
		outlander.setAvailable(false);
		assertFalse(outlander.increaseLife());
		assertEquals(2, outlander.getLifes());

	}

	@Test
	@Order(14)
	void lifesSoldierTest() {

		soldier = new Soldier(0, 0);

		assertEquals(3, soldier.getLifes());

		assertTrue(soldier.decreaseLife());
		assertEquals(2, soldier.getLifes());

		assertTrue(soldier.decreaseLife());
		assertEquals(1, soldier.getLifes());

		assertTrue(soldier.decreaseLife());
		assertEquals(0, soldier.getLifes());

		assertFalse(soldier.decreaseLife());
		assertEquals(0, soldier.getLifes());

		assertTrue(soldier.increaseLife());
		assertEquals(1, soldier.getLifes());

		assertTrue(soldier.increaseLife());
		assertEquals(2, soldier.getLifes());

		assertTrue(soldier.increaseLife());
		assertEquals(3, soldier.getLifes());

		assertTrue(!soldier.increaseLife());
		assertEquals(3, soldier.getLifes());

		soldier.setAvailable(false);
		assertFalse(soldier.decreaseLife());

		soldier.setAvailable(true);
		soldier.setLifes((byte) 2);
		soldier.setAvailable(false);
		assertFalse(soldier.increaseLife());
		assertEquals(2, soldier.getLifes());

	}

	@Test
	@Order(15)
	void lifesConstructorTest() {

		constructor = new Constructor(0, 0);

		assertEquals(3, constructor.getLifes());

		assertTrue(constructor.decreaseLife());
		assertEquals(2, constructor.getLifes());

		assertTrue(constructor.decreaseLife());
		assertEquals(1, constructor.getLifes());

		assertTrue(constructor.decreaseLife());
		assertEquals(0, constructor.getLifes());

		assertFalse(constructor.decreaseLife());
		assertEquals(0, constructor.getLifes());

		assertTrue(constructor.increaseLife());
		assertEquals(1, constructor.getLifes());

		assertTrue(constructor.increaseLife());
		assertEquals(2, constructor.getLifes());

		assertTrue(constructor.increaseLife());
		assertEquals(3, constructor.getLifes());

		assertTrue(!constructor.increaseLife());
		assertEquals(3, constructor.getLifes());

		constructor.setAvailable(false);
		assertFalse(constructor.decreaseLife());

		constructor.setAvailable(true);
		constructor.setLifes((byte) 2);
		constructor.setAvailable(false);
		assertFalse(constructor.increaseLife());
		assertEquals(2, constructor.getLifes());

	}

	@Test
	@Order(16)
	void lifesNinjaTest() {

		ninja = new Ninja(0, 0);

		assertEquals(3, ninja.getLifes());

		assertTrue(ninja.decreaseLife());
		assertEquals(2, ninja.getLifes());

		assertTrue(ninja.decreaseLife());
		assertEquals(1, ninja.getLifes());

		assertTrue(ninja.decreaseLife());
		assertEquals(0, ninja.getLifes());

		assertFalse(ninja.decreaseLife());
		assertEquals(0, ninja.getLifes());

		ninja.setAvailable(false);
		assertFalse(ninja.decreaseLife());

		ninja.setAvailable(true);
		ninja.setLifes((byte) 2);
		ninja.setAvailable(false);
		assertFalse(ninja.increaseLife());

	}
}
