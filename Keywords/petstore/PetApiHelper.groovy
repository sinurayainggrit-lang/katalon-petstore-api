package petstore

import com.kms.katalon.core.annotation.Keyword
import internal.GlobalVariable
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

import java.net.HttpURLConnection
import java.net.URL

class PetApiHelper {

    @Keyword
    static Map request(String method, String path, Map payload = null) {
        String base = GlobalVariable.baseUrl.toString()
        URL url = new URL(base + path)
        HttpURLConnection conn =
            (HttpURLConnection) url.openConnection()

        conn.setRequestMethod(method)
        conn.setConnectTimeout(15000)
        conn.setReadTimeout(15000)
        conn.setRequestProperty('Accept', 'application/json')

        if (payload != null) {
            conn.setDoOutput(true)
            conn.setRequestProperty(
                'Content-Type', 'application/json'
            )
            conn.outputStream.withWriter('UTF-8') {
                it << JsonOutput.toJson(payload)
            }
        }

        int status = conn.responseCode
        InputStream stream = status >= 400 ?
            conn.errorStream : conn.inputStream

        String body = ''
        if (stream != null) {
            body = stream.getText('UTF-8')
            stream.close()
        }
        conn.disconnect()

        def json = null
        try {
            if (body?.trim()) {
                json = new JsonSlurper().parseText(body)
            }
        } catch (Exception ignored) {
            // Response bukan JSON; body tetap tersedia
        }

        return [
            status: status,
            body: body,
            json: json
        ]
    }

    @Keyword
    static Map newPet() {
        long id = System.currentTimeMillis()
        String uniqueName = 'Pet_' + UUID.randomUUID().toString()

        return [
            id: id,
            category: [id: 1, name: 'Dogs'],
            name: uniqueName,
            photoUrls: [],
            tags: [[id: 1, name: 'automation']],
            status: 'available'
        ]
    }
}
