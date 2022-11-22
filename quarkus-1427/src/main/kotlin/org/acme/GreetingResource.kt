package org.acme

import io.smallrye.mutiny.Uni
import org.hibernate.reactive.mutiny.Mutiny
import org.jboss.resteasy.reactive.RestPath
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.*

@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@Path("/hello")
class GreetingResource {

    @field:Inject
    @field:Default
    lateinit var sf: Mutiny.SessionFactory;

    @GET
    fun listAll(): Uni<List<InboundEntity>> {
        return sf.withSession { it.createQuery("From inbd_trans", InboundEntity::class.java).resultList }
    }

    @POST
    fun insert(entity: InboundEntity): Uni<Void> {
        return sf.withTransaction { session ->
            session.persist(entity)
        }
    }

    @PUT
    @Path("{id}")
    fun update(@RestPath id: String, entity: InboundEntity): Uni<Void> {
        return sf.withTransaction { session ->
            session.createQuery<InboundEntity>(
                "UPDATE inbd_trans " +
                        "SET inboundTransaction = :inboundTransaction, " +
                        "createdUserId = :createdUserId " +
                        "WHERE transactionMessageId = :transactionMessageId"
            )
                .setParameter("inboundTransaction", entity.inboundTransaction)
                .setParameter("createdUserId", entity.createdUserId)
                .setParameter("transactionMessageId", id)
                .executeUpdate()
                .replaceWithVoid()
        }
    }
}