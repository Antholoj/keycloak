package org.keycloak.services.resources.admin;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.RealmModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.models.utils.RepresentationToModel;
import org.keycloak.representations.idm.IdentityProviderRepresentation;
import org.keycloak.services.resources.flows.Flows;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Pedro Igor
 */
public class IdentityProviderResource {

    private final RealmModel realm;
    private final KeycloakSession session;
    private final IdentityProviderModel identityProviderModel;

    public IdentityProviderResource(RealmModel realm, KeycloakSession session, IdentityProviderModel identityProviderModel) {
        this.realm = realm;
        this.session = session;
        this.identityProviderModel = identityProviderModel;
    }

    @GET
    @NoCache
    @Produces("application/json")
    public IdentityProviderRepresentation getIdentityProvider() {
        return ModelToRepresentation.toRepresentation(this.identityProviderModel);
    }

    @DELETE
    @NoCache
    public Response delete() {
        this.realm.removeIdentityProviderById(this.identityProviderModel.getId());
        return Response.noContent().build();
    }

    @PUT
    @Consumes("application/json")
    public Response update(IdentityProviderRepresentation model) {
        try {
            this.realm.updateIdentityProvider(RepresentationToModel.toModel(model));
            return Response.noContent().build();
        } catch (ModelDuplicateException e) {
            return Flows.errors().exists("Identity Provider " + model.getId() + " already exists");
        }
    }
}
