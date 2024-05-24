/********* MediaVolume.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import <AVFoundation/AVFoundation.h>

@interface MediaVolume : CDVPlugin {
  // Member variables go here.
}

- (void)getDeviceMediaVolume:(CDVInvokedUrlCommand*)command;
@end

@implementation MediaVolume

- (void)getDeviceMediaVolume:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    [[AVAudioSession sharedInstance] setActive:TRUE error:nil];
    double vol = [[AVAudioSession sharedInstance] outputVolume] * 100;
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDouble:vol];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
