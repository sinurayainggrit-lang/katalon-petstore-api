import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


import petstore.PetApiHelper

def pet = PetApiHelper.newPet()

def create = PetApiHelper.request('POST', '/pet', pet)
assert create.status == 200 : "Create failed: ${create.body}"

pet.name = 'Updated_' + UUID.randomUUID().toString()
pet.status = 'sold'

def response = PetApiHelper.request('PUT', '/pet', pet)

assert response.status == 200 :
	"Expected 200, got ${response.status}: ${response.body}"
assert response.json.id.toString() == pet.id.toString()
assert response.json.name == pet.name
assert response.json.status == 'sold'

println("Updated pet ID: ${pet.id}")
