package edu.uade.tpo.ingsist2.view.proveedorMock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uade.tpo.ingsist2.model.util.EnviarMensajeHelper;
import edu.uade.tpo.ingsist2.utils.mock.MockDataGenerator;
import edu.uade.tpo.ingsist2.view.jms.JMSQueuesNames;
import edu.uade.tpo.ingsist2.view.vo.ListaPreciosVO;
import edu.uade.tpo.ingsist2.view.vo.RecepcionRodamientosVO;

public class EnviarRodamientosMockTest {

	EnviarMensajeHelper emHelper;
	//private static final String PATH_TO_WEB_PROJECT = "/Users/matiasfavale/Documents/WorkspaceJuno/TPO-IngSist2/TPO-IngSist2-WebClient";

	@Before
	public void prepararTest() {
		 emHelper = new EnviarMensajeHelper("127.0.0.1", 1099, JMSQueuesNames.RECEPCION_RODAMIENTOS_QUEUE);
	}

	@Test
	public void basicJMSTest() {
		emHelper.enviarMensaje("TEST - Testeando la cola de mensajes");
	}
	
	
	/**
	 * Se envia una nueva lista de precios a Casa Central * 
	 */
	
	@Test
	public void enviarRodamiento(){
		
		RecepcionRodamientosVO recep = MockDataGenerator.getRandomListaRodamientoVO(30);
		emHelper.enviarMensaje(recep.toXML());
	}
	
	/*
	@Test
	public void enviarRodamientosCreados() {
		int numero = 0;
		File file = null;
		do {
			file = new File(PATH_TO_WEB_PROJECT + "/src/test/ListaPrecios"
					+ numero + ".xml");
			if (file.exists()) {
				numero += 1;
				try {
					emHelper.enviarMensaje(readFileToString(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} while (file.exists());
	}

	@SuppressWarnings("resource")
	private String readFileToString(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			result.append(line);
			result.append("\n");
		}
		return result.toString();
	}

	@SuppressWarnings("unused")
	private void salvarListaPreciosVO(ListaPreciosVO listaPrecios) {
		int numero = 0;

		File file = new File(PATH_TO_WEB_PROJECT + "/src/test/ListaPrecios"
				+ numero + ".xml");
		while (file.exists()) {
			numero += 1;
			file = new File(PATH_TO_WEB_PROJECT + "/src/test/ListaPrecios"
					+ numero + ".xml");
		}
		try {
			Writer writer = new BufferedWriter(new FileWriter(file));
			writer.write(listaPrecios.toXML(true));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  */
	


	@After
	public void finalizarTest() {
		emHelper.cerrarConexion();
	}
}