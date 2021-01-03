# AirConWithMe Binding

This binding is able to control AirconWithMe devices (e.g. Mitsubishi heavy airconditioner).
Devices need to be in the same network as the openhab installation.

## Supported Things

Airconditioners which use AirConWithMe-Interface

## Discovery

Discovery is not supported.


## Thing Configuration

AirConWithMe has the following configuration parameters:

| Name  | Description   | Mandatory                     |
|-------|---------------|-------------------------------|
| host  | Hostname/IP of the indoor AC-Unit to control | yes |
| pollingInterval       | Interval to update values | not |

## Channels
| channel  | type   | description                  | Read/Write
|----------|--------|------------------------------|-----------|
| power  | Switch | Current power settings (Device on/off)  | RW
| quietmode  | Switch | Quitemode active/inactive  | RW
| usermode  | String | Current operating mode (Auto, Cool, Dry,Fan)  | RW
| fanspeed  | String | Current speed of fan (Speed1/Speed2/...)  | RW
| vaneposition  | String | Current vertical position of vane  | RW
| setpointTemp  | Number:Temperature | Temperature to cool/heat to  | RW
| outdoortemp  | Number:Temperature | Temperature at outdoor unit  | R
| currenttemp  | Number:Temperature | Temperature at indoor unit  | R
| ontime  | Number:Temperature | Operating time of indoor unit  | RW



## Full Example

_Provide a full usage example based on textual configuration files (*.things, *.items, *.sitemap)._

## Any custom content here!

_Feel free to add additional sections for whatever you think should also be mentioned about your binding!_
