package io.jenkins.plugins.credentials.secretsmanager;

import hudson.util.Secret;
import io.jenkins.plugins.casc.misc.ConfiguredWithCode;
import io.jenkins.plugins.credentials.secretsmanager.util.CreateSecretOperation;
import org.jenkinsci.plugins.plaincredentials.StringCredentials;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class RolesIT extends AbstractPluginIT {
    @Test
    @ConfiguredWithCode(value = "/roles.yml")
    public void shouldFetchCredentialsFromMultipleAccounts() {
        // Given
        final CreateSecretOperation.Result foo = createStringSecret("supersecret");
        // FIXME put this in another account
        final CreateSecretOperation.Result bar = createOtherStringSecret("supersecret");

        // When
        final List<StringCredentials> credentials = lookupCredentials(StringCredentials.class);

        // Then
        assertThat(credentials)
                .extracting("id", "secret")
                .containsOnly(tuple(foo.getName(), Secret.fromString("supersecret")));
    }
}
