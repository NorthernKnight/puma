package com.dianping.puma.parser.type;

import java.util.List;

import com.dianping.puma.core.util.sql.DMLType;
import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dianping.puma.core.event.ChangedEvent;
import com.dianping.puma.core.event.RowChangedEvent;
import com.dianping.puma.parser.AbstractBaseDebug;

/***
 * enum type test
 * 
 * @author qi.yin
 *
 */
public class EnumTypeDebug extends AbstractBaseDebug {

	private static final String TABLE_NAME = "tb_enum";

	@BeforeClass
	public static void doBefore() throws Exception {
		String create_SQL = "CREATE TABLE IF NOT EXISTS `" + SCHEMA_NAME + "`.`" + TABLE_NAME + "` (\n"
		      + "`id` int NOT NULL AUTO_INCREMENT, \n" + "`default_enum` enum('x','y','z') DEFAULT NULL, \n"
		      + "PRIMARY KEY (`id`)" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
		queryRunner.update(create_SQL);
		setFilterTable(TABLE_NAME);
	}

	@AfterClass
	public static void doAfter() throws Exception {
		String drop_SQL = "DROP TABLE IF EXISTS `" + SCHEMA_NAME + "`.`" + TABLE_NAME + "`";
		queryRunner.update(drop_SQL);
	}

	@Test
	public void enumTypeInsertTest() throws Exception {
		test(new TestLogic() {

			@Override
			public void doLogic() throws Exception {
				String[][] testData = { { "x" }, { "y" }, { "z" } };
				for (int i = 0; i < testData.length; i++) {
					String insert_SQL = "INSERT INTO `" + SCHEMA_NAME + "`.`" + TABLE_NAME + "`(default_enum)VALUES(?)";
					queryRunner.update(insert_SQL, testData[i][0]);
				}
				List<ChangedEvent> events = getEvents(testData.length, false, true, false);
				Assert.assertEquals(testData.length, events.size());
				for (int i = 0; i < testData.length; i++) {
					Assert.assertTrue(events.get(i) instanceof RowChangedEvent);
					RowChangedEvent rowChangedEvent = (RowChangedEvent) events.get(i);
					Assert.assertEquals(DMLType.INSERT, rowChangedEvent.getDmlType());
					Assert.assertEquals(TABLE_NAME, rowChangedEvent.getTable());
					Assert.assertEquals(SCHEMA_NAME, rowChangedEvent.getDatabase());
					Assert.assertEquals(2, rowChangedEvent.getColumns().size());
					Assert.assertEquals(String.valueOf(i + 1),
					      String.valueOf(rowChangedEvent.getColumns().get("default_enum").getNewValue()));
					Assert.assertEquals(null, rowChangedEvent.getColumns().get("default_enum").getOldValue());
				}
			}

		});
	}
}
