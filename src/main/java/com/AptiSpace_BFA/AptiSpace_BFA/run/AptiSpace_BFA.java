package com.AptiSpace_BFA.AptiSpace_BFA.run;

import org.openxava.util.*;

/**
 * Execute this class to start the application.
 */

public class AptiSpace_BFA {

	public static void main(String[] args) throws Exception {
		DBServer.start("AptiSpace_BFA-db"); // To use your own database comment this line and configure src/main/webapp/META-INF/context.xml
		AppServer.run("AptiSpace_BFA"); // Use AppServer.run("") to run in root context
	}

}
