
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import internal.GlobalVariable
import groovy.json.JsonOutput
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.testobject.ConditionType
import petstore.PetApiHelper

def pet = PetApiHelper.newPet()
def response = PetApiHelper.request('POST', '/pet', pet)

assert response.status == 200 :
    "Expected 200, got ${response.status}: ${response.body}"

assert response.json != null
assert response.json.id.toString() == pet.id.toString()
assert response.json.name == pet.name

println("Created pet ID: ${pet.id}")
println("Created pet name: ${pet.name}")
