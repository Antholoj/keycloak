/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keycloak.broker.oidc;

import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.protocol.oidc.representations.OIDCConfigurationRepresentation;
import org.keycloak.util.JsonSerialization;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author Pedro Igor
 */
public class KeycloakOIDCIdentityProviderFactory extends AbstractIdentityProviderFactory<KeycloakOIDCIdentityProvider> {

    public static final String PROVIDER_ID = "keycloak-oidc";

    @Override
    public String getName() {
        return "Keycloak OpenID Connect";
    }

    @Override
    public KeycloakOIDCIdentityProvider create(IdentityProviderModel model) {
        return new KeycloakOIDCIdentityProvider(new OIDCIdentityProviderConfig(model));
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public Map<String, String> parseConfig(InputStream inputStream) {
        return OIDCIdentityProviderFactory.parseOIDCConfig(inputStream);

    }
}
