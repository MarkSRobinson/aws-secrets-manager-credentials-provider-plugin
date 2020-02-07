package io.jenkins.plugins.credentials.secretsmanager.config;

import io.jenkins.plugins.credentials.secretsmanager.util.FormValidationResult;
import io.jenkins.plugins.credentials.secretsmanager.util.JenkinsConfiguredWithWebRule;
import io.jenkins.plugins.credentials.secretsmanager.util.PluginConfigurationForm;
import org.junit.Rule;

import java.util.concurrent.atomic.AtomicReference;

public class CheckEndpointConfigurationWebIT extends AbstractCheckEndpointConfigurationIT {

    @Rule
    public final JenkinsConfiguredWithWebRule r = new JenkinsConfiguredWithWebRule();

    @Override
    protected FormValidationResult validate(String serviceEndpoint, String signingRegion) {
        final AtomicReference<FormValidationResult> result = new AtomicReference<>();

        r.configure(f -> {
            final PluginConfigurationForm form = new PluginConfigurationForm(f);

            form.setEndpointConfiguration(serviceEndpoint, signingRegion);

            final FormValidationResult r = form.clickValidateButton("Test Endpoint Configuration");
            result.set(r);
        });

        return result.get();
    }
}
