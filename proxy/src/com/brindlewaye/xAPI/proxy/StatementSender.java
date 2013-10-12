package com.brindlewaye.xAPI.proxy;

/**
 * 
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.UUID;

import com.rusticisoftware.tincan.Activity;
import com.rusticisoftware.tincan.Agent;
import com.rusticisoftware.tincan.RemoteLRS;
import com.rusticisoftware.tincan.Statement;
import com.rusticisoftware.tincan.TCAPIVersion;
import com.rusticisoftware.tincan.Verb;
import com.rusticisoftware.tincan.json.StringOfJSON;

/**
 * @author Dave
 *
 */
public class StatementSender {

	private static final Properties config = new Properties();
	private RemoteLRS remote = null;

	/**
	 * 
	 */
	public StatementSender() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StatementSender ss = new StatementSender();
		try {
			ss.initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (args.length == 0) {
			ss.sendTestStatement();
		} else {
			System.out.println(args[0]);
			UUID uuid = ss.sendStatement(args[0], null);
			Statement st = ss.retrieveStatement(uuid);
			String s;
			try {
				s = st.toJSON(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				s = "";
			}
			System.out.print(s);
		}
	}
	
	public Statement retrieveStatement(UUID uuid) {
		if (remote == null) return null;
		
		try {
			Statement stmt = remote.retrieveStatement(uuid.toString());
			return stmt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public UUID sendStatement(String statement, UUID id) {
		StringOfJSON stringOfJSON = new StringOfJSON(statement);
		Statement stmt = null;
		UUID uuid = null;

		try 
                {
			if (remote == null) initialize();
		} 
                catch (IOException e) 
                {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return uuid;
		}
                
		try 
                {
			stmt = new Statement(stringOfJSON);
			stmt.setVersion(TCAPIVersion.V100);
			stmt.setId(id);
		} 
                catch (IOException e)
                {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return uuid;
		}
                catch ( URISyntaxException e)
                {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return uuid;                    
                }
		
		if (remote == null) return null;
		
		try {
			uuid = remote.saveStatement(stmt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uuid;
	}

	public RemoteLRS initialize() throws IOException {
		remote = new RemoteLRS();
		LoadConfig();
		remote.setEndpoint(config.getProperty("endpoint"));
		remote.setUsername(config.getProperty("username"));
		remote.setPassword(config.getProperty("password"));
		remote.setVersion(TCAPIVersion.V100);
		return remote;
	}
        
        public RemoteLRS initializeFromClasspath() throws IOException {
		remote = new RemoteLRS();
		LoadConfigFromClasspath();
		remote.setEndpoint(config.getProperty("endpoint"));
		remote.setUsername(config.getProperty("username"));
		remote.setPassword(config.getProperty("password"));
		remote.setVersion(TCAPIVersion.V100);
		return remote;
	}
        
	
	public void sendTestStatement() {
		try {
			if (remote == null) initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Statement stmt = new Statement();
		Agent agent = new Agent();
		Verb verb = new Verb();
		Activity action = new Activity();
		stmt.setActor(agent);
		stmt.setVerb(verb);
		stmt.setObject(action);
	}

	private void LoadConfig() throws IOException {
		String path = System.getProperty("user.dir");
		String props = path + "\\lrs.properties";
        //InputStream is = RemoteLRS.class.getResourceAsStream(props);
		InputStream is = new FileInputStream(props);
        config.load(is);
        is.close();
	}
       
// file is picked from classpath, i.e in classes folder, or any other directory which lies in classpath.
        private void LoadConfigFromClasspath() throws IOException 
        {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("lrs.properties");
                config.load(is);
                is.close();
	}
}
