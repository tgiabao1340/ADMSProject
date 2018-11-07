package application.entities;

import java.io.Serializable;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ProductGen implements IdentifierGenerator {

	String prefixMT = "XM";
	String prefixRP = "LK";
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		if(object.getClass().getName()==(Motorbike.class.getName()))
		{
				String query = "SELECT e.id FROM Motorbike e";
		        Stream<String> ids = session.createQuery(query, String.class).stream();
		        Long max = ids.map(o -> o.replace(prefixMT, "")).mapToLong(Long::parseLong).max().orElse(0L);
		        System.out.println(prefixMT + (String.format("%04d", max + 1)));
		        return prefixMT + (String.format("%04d", max + 1));
		}
		if(object.getClass().getName()==(Replacement.class.getName()))
		{
			String query = "SELECT e.id FROM Replacement e";
	        Stream<String> ids = session.createQuery(query, String.class).stream();
	        Long max = ids.map(o -> o.replace(prefixRP, "")).mapToLong(Long::parseLong).max().orElse(0L);
	        System.out.println(prefixRP + (String.format("%04d", max + 1)));
	        return prefixRP + (String.format("%04d", max + 1));
		}
		else
		return null;
	}

}
