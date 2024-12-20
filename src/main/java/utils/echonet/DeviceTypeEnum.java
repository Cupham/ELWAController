/*******************************************************************************
 * Copyright 2019 Cu Pham
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package utils.echonet;

public enum DeviceTypeEnum {
	GasLeakSensor(0x00 << 8 | 0x01),
	CrimePreventionSensor(0x00 << 8 | 0x02),
	EmergencyButton(0x00 << 8 | 0x03),
	FirstAidSensor(0x00 << 8 | 0x04),
	EarthquakeSensor(0x00 << 8 | 0x05),
	ElectricLeakSensor(0x00 << 8 | 0x06),
	HumanDetectionSensor(0x00 << 8 | 0x07),
	VisitorDetectionSensor(0x00 << 8 | 0x08),
	CallSensor(0x00 << 8 | 0x09),
	CondensationSensor(0x00 << 8 | 0x0A),
	AirPollutionSensor(0x00 << 8 | 0x0B),
	OxygenSensor(0x00 << 8 | 0x0C),
	IlluminanceSensor(0x00 << 8 | 0x0D),
	SoundSensor(0x00 << 8 | 0x0E),
	MailingSensor(0x00 << 8 | 0x0F),
	WeightSensor(0x00 << 8 | 0x10),
	TemperatureSensor(0x00 << 8 | 0x11),
	HumiditySensor(0x00 << 8 | 0x12),
	RainSensor(0x00 << 8 | 0x13),
	WaterLevelSensor(0x00 << 8 | 0x14),
	BathWaterLevelSensor(0x00 << 8 | 0x15),
	BathHeatingStatusSensor(0x00 << 8 | 0x16),
	WaterLeakSensor(0x00 << 8 | 0x17),
	WaterOverFlowSensor(0x00 << 8 | 0x18),
	FireSensor(0x00 << 8 | 0x19),
	CigaretteSmokeSensor(0x00 << 8 | 0x1A),
	CO2Sensor(0x00 << 8 | 0x1B),
	GasSensor(0x00 << 8 | 0x1C),
	VOCSensor(0x00 << 8 | 0x1D),
	DifferentialPressureSensor(0x00 << 8 | 0x1E),
	AirSpeedSensor(0x00 << 8 | 0x1F),
	OdorSensor(0x00 << 8 | 0x20),
	FlameSensor(0x00 << 8 | 0x21),
	ElectricEnergySensor(0x00 << 8 | 0x22),
	CurrentValueSensor(0x00 << 8 | 0x23),
	WaterFlowRateSensor(0x00 << 8 | 0x25),
	MicroMotionSensor(0x00 << 8 | 0x26),
	PassageSensor(0x00 << 8 | 0x27),
	BedPresenceSensor(0x00 << 8 | 0x28),
	OpenCloseSensor(0x00 << 8 | 0x29),
	ActivityMountSensor(0x00 << 8 | 0x2A),
	HumanBodyLocationSensor(0x00 << 8 | 0x2B),
	SnowSensor(0x00 << 8 | 0x2C),
	AirPressureSensor(0x00 << 8 | 0x2D),

	HomeAirconditioner(0x01 << 8 | 0x30),
	VentilationFan(0x01 << 8 | 0x33),
	AirconditionerVentilationFan(0x01 << 8 | 0x34),
	AirCleaner(0x01 << 8 | 0x35),
	Humidifier(0x01 << 8 | 0x39),
	ElectricHeater(0x01 << 8 | 0x42),
	FanHeater(0x01 << 8 | 0x43),
	ElectricStorageHeater(0x01 << 8 | 0x55),
	CommercialAirconditionerIndoorUnit(0x01 << 8 | 0x56),
	CommercialAirconditionerOutdoorUnit(0x01 << 8 | 0x57),
	GasHeatCommercialAirconditionerIndoorUnit(0x01 << 8 | 0x58),
	GasHeatCommercialAirconditionerOutdoorUnit(0x01 << 8 | 0x59),

	ElectricBlind(0x02 << 8 | 0x60),
	ElectricShutter(0x02 << 8 | 0x61),
	ElectricCurtain(0x02 << 8 | 0x62),
	ElectricRainSlidingDoor(0x02 << 8 | 0x63),
	ElectricGate(0x02 << 8 | 0x64),
	ElectricWindow(0x02 << 8 | 0x65),
	ElectricEntranceDoor(0x02 << 8 | 0x66),
	GardenSprinkler(0x02 << 8 | 0x67),
	ElectricWaterHeater(0x02 << 8 | 0x6B),
	BidetEquippedToilet(0x02 << 8 | 0x6E),
	ElectricLock(0x02 << 8 | 0x6F),
	InstantaneousWaterHeater(0x02 << 8 | 0x72),
	BathRoomDryer(0x02 << 8 | 0x73),
	HomeSolarPowerGeneration(0x02 << 8 | 0x79),
	ColdHotWaterHeatSourceEquipment(0x02 << 8 | 0x7A),
	FloorHeater(0x02 << 8 | 0x7B),

	FuelCell(0x02 << 8 | 0x7C),
	StorageBattery(0x02 << 8 | 0x7D),
	ElectricVehicleChargerDischarger(0x02 << 8 | 0x7E),
	EngineCogeneration(0x02 << 8 | 0x7F),
	ElectricEnergyMeter(0x02 << 8 | 0x80),
	WaterFlowMeter(0x02 << 8 | 0x81),
	GasMeter(0x02 << 8 | 0x82),
	LPGasMeter(0x02 << 8 | 0x83),
	DistributionPanelMetering(0x02 << 8 | 0x87),
	LowVoltageSmartElectricEnergyMeter(0x02 << 8 | 0x88),
	SmartGasMeter(0x02 << 8 | 0x89),
	HighVoltageSmartElectricEnergyMeter(0x02 << 8 | 0x8A),
	KeroseneMeter(0x02 << 8 | 0x8B),
	SmartKeroseneMeter(0x02 << 8 | 0x8C),
	GeneralLight(0x02 << 8 | 0x90),
	SingleFunctionLighting(0x02 << 8 | 0x91),
	LightingForSolidLightEmittingSource(0x02 << 8 | 0x92),
	Buzzer(0x02 << 8 | 0xA0),
	ElectricVehicleCharger(0x02 << 8 | 0xA1),
	HouseholdSmallWindTurbinePowerGeneration(0x02 << 8 | 0xA2),
	ExtendedLightingSystem(0x02 << 8 | 0xA4),
	MultipleInputPCS(0x02 << 8 | 0xA5),

	HotWaterPot(0x03 << 8 | 0xB2),
	Refrigerator(0x03 << 8 | 0xB7),
	MicrowaveOven(0x03 << 8 | 0xB8),
	CookingHeater(0x03 << 8 | 0xB9),
	RiceCooker(0x03 << 8 | 0xBB),
	WashingMachine(0x03 << 8 | 0xC5),
	ClothesDryer(0x03 << 8 | 0xC6),
	CommercialShowcase(0x03 << 8 | 0xCE),
	WasherDryer(0x03 << 8 | 0xD3),
	CommercialShowcaseOutdoorUnit(0x03 << 8 | 0xD4),

	WeighingMachine(0x04 <<8 |0x01),

	Switch(0x05 <<8 | 0xFD),
	Controller(0x05 <<8 | 0xFF),
	DREventController(0x05 <<8 | 0xFB),
	ParallelProcessingCombinationTypePowerControl(0x05 <<8 | 0xFA),

	Display(0x06 << 8 | 0x01),
	Television(0x06 << 8 | 0x02),
	Audio(0x06 << 8 | 0x03),
	NetworkCamera(0x06 << 8 | 0x04),

	Profile(0x0e << 8 | 0xf0),
	SuperObject(0xffff);





	private int code;
	private DeviceTypeEnum(int code) {
		this.code = code;
	}
	public byte code() {
		return (byte) code;
	}

}
