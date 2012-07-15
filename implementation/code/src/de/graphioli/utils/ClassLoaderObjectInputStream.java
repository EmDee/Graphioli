package de.graphioli.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * This class is used for unserializing objects that need to be loaded by a 
 * specific class loader.
 * 
 * @author Team Graphioli
 *
 */
public class ClassLoaderObjectInputStream extends ObjectInputStream {
	
	/**
	 * The class loader.
	 */
	private final ClassLoader classLoader; 

	/**
	 * Creates a {@code ClassLoaderObjectInputStream} based on the given input stream
	 * and class loader.
	 * @param in the input stream
	 * @param classLoader the class loader
	 * @throws IOException when accessing the input stream fails
	 */
	public ClassLoaderObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException {
		super(in);
		this.classLoader = classLoader;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
		return Class.forName(desc.getName(), false, this.classLoader);
	}

}
