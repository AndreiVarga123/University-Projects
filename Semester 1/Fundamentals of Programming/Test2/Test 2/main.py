from Repository.TaxiRepository import TaxiRepository
from Service.TaxiService import TaxiService
from UI.UI import UI

taxi_repository = TaxiRepository()
taxi_service = TaxiService(taxi_repository)
ui = UI(taxi_service)
ui.start()
