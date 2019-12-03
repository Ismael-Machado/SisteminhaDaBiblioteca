function verificar(xhr, status, args, dlg1) {
	if(args.validationFailed) {
		PF(dlg).jq.effect("shake", {times:5}, 100);
	}
	else {
		PF(dlg1).hide();
	}
}