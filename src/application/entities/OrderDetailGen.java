package application.entities;

import java.io.Serializable;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OrderDetailGen implements IdentifierGenerator {
	String prefix = "";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object arg1) throws HibernateException {
		String query = "SELECT e.OrderDetailID FROM OrderDetail e";
		Stream<String> ids = session.createQuery(query, String.class).stream();
		Long max = ids.map(o -> o.replace(prefix, "")).mapToLong(Long::parseLong).max().orElse(0L);
		System.out.println(prefix + (String.format("%04d", max + 1)));
		return prefix + (String.format("%04d", max + 1));
	}

}
