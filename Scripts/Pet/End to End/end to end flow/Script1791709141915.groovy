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

// 1. CREATE
def pet = PetApiHelper.newPet()
def create = PetApiHelper.request('POST', '/pet', pet)

assert create.status == 200
assert create.json.id.toString() == pet.id.toString()
assert create.json.name == pet.name

// 2. READ initial data
def getInitial = PetApiHelper.request('GET', "/pet/${pet.id}")

assert getInitial.status == 200
assert getInitial.json.name == pet.name

// 3. UPDATE
pet.name = 'Updated_' + UUID.randomUUID().toString()
pet.status = 'sold'

def update = PetApiHelper.request('PUT', '/pet', pet)

assert update.status == 200
assert update.json.name == pet.name
assert update.json.status == 'sold'

// 4. READ updated data
def getUpdated = PetApiHelper.request('GET', "/pet/${pet.id}")

assert getUpdated.status == 200
assert getUpdated.json.name == pet.name
assert getUpdated.json.status == 'sold'

// 5. DELETE
def delete = PetApiHelper.request('DELETE', "/pet/${pet.id}")

assert delete.status == 200

// 6. VERIFY deletion
def getDeleted = PetApiHelper.request('GET', "/pet/${pet.id}")

assert getDeleted.status == 404 :
	"Expected 404 after deletion, got ${getDeleted.status}"

println("E2E CRUD passed for pet ID: ${pet.id}")
